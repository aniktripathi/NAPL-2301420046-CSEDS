package main

import (
	"fmt"

	"example.com/app/mathutil"
	"example.com/app/strop"
)

func main() {
	fmt.Println("The sum is:", mathutil.Add(5, 10))
	fmt.Println("The factorial is:", mathutil.Fact(5))
	fmt.Println("The power is:", mathutil.Pow(2, 4))
	fmt.Println("The reversed string is:", strop.Reverse("Go Programming"))
	fmt.Println("The vowel count is:", strop.CountVowels("Go Programming"))
}
