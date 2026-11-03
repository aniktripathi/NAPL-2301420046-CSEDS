open System
open System.Text.RegularExpressions

// 1. Reverse and Palindrome
let reverseString (s:string) =
    new string(Array.rev (s.ToCharArray()))

let isPalindrome (s:string) =
    s = reverseString s

// 2. String Transformation
let transform (s:string) =
    if s |> Seq.forall Char.IsLetter then
        s.ToUpper()
    elif s |> Seq.forall Char.IsDigit then
        s + s
    else
        s

// 3. Email Validation
let isValidEmail (s:string) =
    Regex.IsMatch(s, @"^[\w.-]+@[\w.-]+\.\w+$")

// Main
printfn "Reverse and Palindrome:"
["madam"; "hello"; "level"]
|> List.iter (fun s ->
    printfn "%s -> Reverse: %s, Palindrome: %b"
        s (reverseString s) (isPalindrome s))

printfn "\nString Transformation:"
["hello"; "anik"; "anik123"]
|> List.iter (fun s ->
    printfn "%s -> %s" s (transform s))

printfn "\nEmail Validation:"
["anik@gmail.com"; "anuj@yahoo.com"; "invalid-email"]
|> List.iter (fun s ->
    printfn "%s -> %b" s (isValidEmail s))