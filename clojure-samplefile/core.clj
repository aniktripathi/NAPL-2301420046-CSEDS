(ns my-first-app.core)

(defn -main []

 
  (def numbers [1 2 3 4 5 6 7 8 9 10])

  (println "\n--- FIRST COLLECTION ---")
  (println "Original Collection:" numbers)

 
  (println "Squares:" (map #(* % %) numbers))

  ;; 2. FILTER - Select even numbers
  (println "Even Numbers:" (filter even? numbers))

  ;; 3. REDUCE - Calculate sum
  (println "Sum:" (reduce + numbers))

  ;; 4. TAKE - Get first 5 elements
  (println "Take 5:" (take 5 numbers))

  ;; 5. DROP - Remove first 5 elements
  (println "Drop 5:" (drop 5 numbers))

  ;; 6. THREADING MACRO - Chain operations
  (println "Threading Result:"
           (->> numbers
                (filter even?)
                (map #(* % %))
                (take 3)))

  ;; Second Collection
  (def numbers2 [12 15 18 21 24 27 30])

  (println "\n--- SECOND COLLECTION ---")
  (println "Original Collection:" numbers2)

  ;; MAP
  (println "Squares:" (map #(* % %) numbers2))

  ;; FILTER
  (println "Even Numbers:" (filter even? numbers2))

  ;; REDUCE
  (println "Maximum:" (reduce max numbers2))

  ;; TAKE
  (println "Take 3:" (take 3 numbers2))

  ;; DROP
  (println "Drop 3:" (drop 3 numbers2))

  ;; THREADING MACRO
  (println "Threading Result:"
           (->> numbers2
                (filter even?)
                (map inc)
                (take 3)))

  (println "\nProgram Completed Successfully!"))