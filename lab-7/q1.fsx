open System

let task1 =
    async {
        printfn "Task 1 Started"
        do! Async.Sleep(2000)
        printfn "Task 1 Completed"
        return "Task 1 Result: Data Downloaded"
    }

let task2 =
    async {
        printfn "Task 2 Started"
        do! Async.Sleep(1000)
        printfn "Task 2 Completed"
        return "Task 2 Result: Data Processed"
    }

let task3 =
    async {
        printfn "Task 3 Started"
        do! Async.Sleep(1500)
        printfn "Task 3 Completed"
        return "Task 3 Result: File Saved"
    }

let results =
    [task1; task2; task3]
    |> Async.Parallel
    |> Async.RunSynchronously

printfn "\nAll Results:"
results |> Array.iter (printfn "%s")