package main

import (
	"fmt"
	"time"
)

// Goroutine 1: Calculate squares
func square(ch chan string) {
	fmt.Println("Square Goroutine Started")
	for i := 1; i <= 5; i++ {
		ch <- fmt.Sprintf("Square of %d = %d", i, i*i)
		time.Sleep(100 * time.Millisecond)
	}
	fmt.Println("Square Goroutine Completed")
}

// Goroutine 2: Calculate cubes
func cube(ch chan string) {
	fmt.Println("Cube Goroutine Started")
	for i := 1; i <= 5; i++ {
		ch <- fmt.Sprintf("Cube of %d = %d", i, i*i*i)
		time.Sleep(100 * time.Millisecond)
	}
	fmt.Println("Cube Goroutine Completed")
}

// Goroutine 3: Calculate Fibonacci
func fibonacci(ch chan string) {
	fmt.Println("Fibonacci Goroutine Started")

	a, b := 0, 1

	for i := 1; i <= 5; i++ {
		ch <- fmt.Sprintf("Fibonacci = %d", a)
		a, b = b, a+b
		time.Sleep(100 * time.Millisecond)
	}

	fmt.Println("Fibonacci Goroutine Completed")
}

func main() {
	ch := make(chan string)

	fmt.Println("Main Function Started")

	// Launch three goroutines
	go square(ch)
	go cube(ch)
	go fibonacci(ch)

	// Collect and print 15 results
	for i := 0; i < 15; i++ {
		result := <-ch
		fmt.Println(result)
	}

	fmt.Println(" Function Completed")
}
