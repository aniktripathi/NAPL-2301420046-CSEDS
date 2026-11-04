let square nums =
    List.map (fun x -> x * x) nums

// List.filter - Select even numbers
let even nums =
    List.filter (fun x -> x % 2 = 0) nums

// List.fold - Calculate sum
let sum nums =
    List.fold (fun acc x -> acc + x) 0 nums

// Test Lists
let list1 = [1; 2; 3; 4; 5]
let list2 = [2; 4; 6; 8]

// Display Results
printfn "List.map:"
printfn "%A" (square list1)
printfn "%A" (square list2)

printfn "\nList.filter:"
printfn "%A" (even list1)
printfn "%A" (even list2)

printfn "\nList.fold:"
printfn "%d" (sum list1)
printfn "%d" (sum list2)