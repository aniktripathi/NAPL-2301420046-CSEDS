#r "nuget: Microsoft.Data.Sqlite"

open Microsoft.Data.Sqlite

// Student Record
type Student = {
    Id: int
    Name: string
    Age: int
    Course: string
}
let connectionString = "Data Source=students.db"

// Create Table
let createTable () =
    async {
        use con = new SqliteConnection(connectionString)
        do! con.OpenAsync() |> Async.AwaitTask

        use cmd = con.CreateCommand()
        cmd.CommandText <-
            "CREATE TABLE IF NOT EXISTS Students
             (Id INTEGER PRIMARY KEY, Name TEXT, Age INTEGER, Course TEXT)"

        let! _ = cmd.ExecuteNonQueryAsync() |> Async.AwaitTask

        // Clear old records to avoid duplicate IDs
        cmd.CommandText <- "DELETE FROM Students"
        let! _ = cmd.ExecuteNonQueryAsync() |> Async.AwaitTask

        return ()
    }

// Add Student
let addStudent s =
    async {
        use con = new SqliteConnection(connectionString)
        do! con.OpenAsync() |> Async.AwaitTask

        use cmd = con.CreateCommand()
        cmd.CommandText <-
            "INSERT INTO Students VALUES ($id,$name,$age,$course)"

        cmd.Parameters.AddWithValue("$id", s.Id) |> ignore
        cmd.Parameters.AddWithValue("$name", s.Name) |> ignore
        cmd.Parameters.AddWithValue("$age", s.Age) |> ignore
        cmd.Parameters.AddWithValue("$course", s.Course) |> ignore

        let! _ = cmd.ExecuteNonQueryAsync() |> Async.AwaitTask
        return ()
    }

// Read Students
let getStudents () =
    async {
        use con = new SqliteConnection(connectionString)
        do! con.OpenAsync() |> Async.AwaitTask

        use cmd = con.CreateCommand()
        cmd.CommandText <- "SELECT * FROM Students"

        use! reader = cmd.ExecuteReaderAsync() |> Async.AwaitTask

        let students = ResizeArray<Student>()

        while reader.Read() do
            students.Add({
                Id = reader.GetInt32(0)
                Name = reader.GetString(1)
                Age = reader.GetInt32(2)
                Course = reader.GetString(3)
            })

        return List.ofSeq students
    }

// Update Student
let updateStudent id age =
    async {
        use con = new SqliteConnection(connectionString)
        do! con.OpenAsync() |> Async.AwaitTask

        use cmd = con.CreateCommand()
        cmd.CommandText <- "UPDATE Students SET Age=$age WHERE Id=$id"

        cmd.Parameters.AddWithValue("$age", age) |> ignore
        cmd.Parameters.AddWithValue("$id", id) |> ignore

        let! _ = cmd.ExecuteNonQueryAsync() |> Async.AwaitTask
        return ()
    }

// Delete Student
let deleteStudent id =
    async {
        use con = new SqliteConnection(connectionString)
        do! con.OpenAsync() |> Async.AwaitTask

        use cmd = con.CreateCommand()
        cmd.CommandText <- "DELETE FROM Students WHERE Id=$id"

        cmd.Parameters.AddWithValue("$id", id) |> ignore

        let! _ = cmd.ExecuteNonQueryAsync() |> Async.AwaitTask
        return ()
    }

// Main Program
let main =
    async {

        do! createTable()

        // AddStudents
        do! addStudent {
            Id = 1
            Name = "Anik"
            Age = 21
            Course = "Data Science"
        }

        do! addStudent {
            Id = 2
            Name = "Rahul"
            Age = 19
            Course = "Computer Science"
        }

        do! addStudent {
            Id = 3
            Name = "Aman"
            Age = 17
            Course = "IT"
        }

        // Display Students
        let! students = getStudents()

        printfn "\nAll Students:"
        students |> List.iter (printfn "%A")

        //map
        let names = students |> List.map (fun s -> s.Name)

        printfn "\nStudent Names:"
        printfn "%A" names

        //filter
        let adults = students |> List.filter (fun s -> s.Age >= 18)

        printfn "\nAdult Students:"
        adults |> List.iter (printfn "%A")

        // Pattern
        printfn "\nAge Checking:"

        students |> List.iter (fun s ->
            match s.Age with
            | age when age >= 18 ->
                printfn "%s is an Adult" s.Name
            | _ ->
                printfn "%s is a Minor" s.Name
        )

        // Update Student
        do! updateStudent 1 22

        // Delete Student
        do! deleteStudent 3

        // Display Updated
        let! updated = getStudents()

        printfn "\nAfter Update and Delete:"
        updated |> List.iter (printfn "%A")
    }

// RunWorkflow
main |> Async.RunSynchronously