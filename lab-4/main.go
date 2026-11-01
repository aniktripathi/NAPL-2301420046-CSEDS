package main

import (
	"fmt"
	"math"
)

func Divide(a, b float64) (float64, error) {
	if b == 0 {
		return 0, fmt.Errorf("division by zero")
	}
	return a / b, nil
}

func SquareRoot(n float64) (float64, error) {
	if n < 0 {
		return 0, fmt.Errorf("negative square root")
	}
	return math.Sqrt(n), nil
}

func CheckInput(s string) error {
	if s == "" {
		return fmt.Errorf("empty input")
	}
	return nil
}

func main() {
	r, e := Divide(10, 2)
	fmt.Println(r, e)

	_, e = Divide(10, 0)
	fmt.Println(e)

	r, e = SquareRoot(25)
	fmt.Println(r, e)

	_, e = SquareRoot(-9)
	fmt.Println(e)

	fmt.Println(CheckInput(""))
	fmt.Println(CheckInput("Anik"))
}
