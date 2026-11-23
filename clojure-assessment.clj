(ns data-pipeline.core
  (:require [cheshire.core :as json]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :as response])
  (:import [java.sql DriverManager SQLException]))

(def db-url
  (or (System/getenv "DATABASE_URL")
      "jdbc:sqlite:students.db"))

(def students
  [{:id 1 :name "Anik" :marks 85 :course "CSE"}
   {:id 2 :name "Rahul" :marks 45 :course "IT"}
   {:id 3 :name "Aman" :marks 92 :course "CSE"}
   {:id 4 :name "Priya" :marks 76 :course "IT"}
   {:id 5 :name "Rohit" :marks 35 :course "CSE"}])

(defn process-data
  "Label students as pass/fail and calculate class summary statistics."
  [data]
  (let [mapped-data (map (fn [student]
                           (assoc student :result
                                  (if (>= (:marks student) 40)
                                    "PASS"
                                    "FAIL")))
                         data)
        mapped-data (vec mapped-data)
        passed (filter #(= "PASS" (:result %)) mapped-data)
        passed (vec passed)
        total-marks (reduce (fn [total student]
                              (+ total (:marks student)))
                            0
                            mapped-data)
        total-students (count mapped-data)]
    {:students mapped-data
     :passed passed
     :total-students total-students
     :passed-count (count passed)
     :failed-count (- total-students (count passed))
     :total-marks total-marks
     :average (if (pos? total-students)
                (double (/ total-marks total-students))
                0.0)}))

(defn- create-table! [database-url]
  (with-open [connection (DriverManager/getConnection database-url)
              statement (.createStatement connection)]
    (.executeUpdate statement
                    "CREATE TABLE IF NOT EXISTS students
                     (id INTEGER PRIMARY KEY,
                      name TEXT NOT NULL,
                      marks INTEGER NOT NULL,
                      course TEXT NOT NULL)")
    (.executeUpdate statement
                    "CREATE TABLE IF NOT EXISTS processed_students
                     (id INTEGER PRIMARY KEY,
                      name TEXT NOT NULL,
                      marks INTEGER NOT NULL,
                      course TEXT NOT NULL,
                      result TEXT NOT NULL)")))

(defn- insert-student! [database-url student]
  (with-open [connection (DriverManager/getConnection database-url)
              statement (.prepareStatement
                         connection
                         "INSERT OR REPLACE INTO students
                          (id, name, marks, course) VALUES (?, ?, ?, ?)")]
    (.setInt statement 1 (:id student))
    (.setString statement 2 (:name student))
    (.setInt statement 3 (:marks student))
    (.setString statement 4 (:course student))
    (.executeUpdate statement)))

(defn read-students
  "Read source student records from SQLite."
  [database-url]
  (with-open [connection (DriverManager/getConnection database-url)
              statement (.createStatement connection)
              result-set (.executeQuery
                          statement
                          "SELECT id, name, marks, course
                           FROM students ORDER BY id")]
    (loop [result []]
      (if (.next result-set)
        (recur (conj result
                     {:id (.getInt result-set "id")
                      :name (.getString result-set "name")
                      :marks (.getInt result-set "marks")
                      :course (.getString result-set "course")}))
        result))))

(defn- save-processed! [database-url data]
  (with-open [connection (DriverManager/getConnection database-url)]
    (.setAutoCommit connection false)
    (try
      (with-open [statement (.createStatement connection)]
        (.executeUpdate statement "DELETE FROM processed_students"))
      (with-open [statement (.prepareStatement
                             connection
                             "INSERT INTO processed_students
                              (id, name, marks, course, result)
                              VALUES (?, ?, ?, ?, ?)")]
        (doseq [student data]
          (.setInt statement 1 (:id student))
          (.setString statement 2 (:name student))
          (.setInt statement 3 (:marks student))
          (.setString statement 4 (:course student))
          (.setString statement 5 (:result student))
          (.addBatch statement))
        (.executeBatch statement))
      (.commit connection)
      (catch SQLException error
        (try
          (.rollback connection)
          (catch SQLException rollback-error
            (.addSuppressed error rollback-error)))
        (throw error)))))

(defn read-processed
  "Read the previously processed student records from SQLite."
  [database-url]
  (with-open [connection (DriverManager/getConnection database-url)
              statement (.createStatement connection)
              result-set (.executeQuery
                          statement
                          "SELECT id, name, marks, course, result
                           FROM processed_students ORDER BY id")]
    (loop [result []]
      (if (.next result-set)
        (recur (conj result
                     {:id (.getInt result-set "id")
                      :name (.getString result-set "name")
                      :marks (.getInt result-set "marks")
                      :course (.getString result-set "course")
                      :result (.getString result-set "result")}))
        result))))

(defn initialize!
  "Create tables and insert the sample records when the source table is empty."
  [database-url]
  (create-table! database-url)
  (when (empty? (read-students database-url))
    (doseq [student students]
      (insert-student! database-url student))))

(defn- json-response [data status]
  (-> (response/response (json/generate-string data))
      (response/status status)
      (response/content-type "application/json")))

(defn make-handler [database-url]
  (fn [{:keys [uri]}]
    (try
      (case uri
        "/" (json-response
             {:message "Student Data Pipeline API Running"}
             200)

        "/api/students" (json-response
                         (read-students database-url)
                         200)

        "/api/process" (let [processed (process-data
                                        (read-students database-url))]
                         (save-processed! database-url
                                          (:students processed))
                         (json-response processed 200))

        "/api/processed" (json-response
                          (read-processed database-url)
                          200)

        (json-response {:error "Endpoint not found"} 404))
      (catch SQLException error
        (binding [*out* *err*]
          (println "Database request failed:" (.getMessage error)))
        (json-response {:error "Database operation failed"} 500)))))

(def app
  (make-handler db-url))

(defn -main [& _args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (try
      (initialize! db-url)
      (catch SQLException error
        (binding [*out* *err*]
          (println "Database initialization failed:" (.getMessage error)))
        (throw error)))
    (println (str "Server running at http://localhost:" port))
    (run-jetty (make-handler db-url) {:port port :join? true})))
