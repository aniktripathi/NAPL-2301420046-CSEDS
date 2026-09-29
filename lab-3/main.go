package main

import "fmt"

type Student struct {
	Name string
	Age  int
}
func modifyValue(num *int) {
	*num = *num + 10
}
//1
func main() {

	var x int = 20
	var ptr *int = &x

	fmt.Println("Original v:", x)
	fmt.Println("Address x:", &x)
	fmt.Println("Pointer stores address:", ptr)
	fmt.Println("Value using dereferencing:", *ptr)

	fmt.Println("\nBefore function call:", x)
//2
	modifyValue(&x)

	fmt.Println("After function call:", x)
//3
	s1 := new(Student)

	s1.Name = "Anik"
	s1.Age = 21

	fmt.Println("\nStudent Details:")
	fmt.Println("Name:", s1.Name)
	fmt.Println("Age:", s1.Age)
}

