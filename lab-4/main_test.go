package main

import "testing"

func TestDivide(t *testing.T) {
	r, e := Divide(10, 2)
	if e != nil || r != 5 {
		t.Error("Division failed")
	}
}

func TestDivideZero(t *testing.T) {
	_, e := Divide(10, 0)
	if e == nil {
		t.Error("Expected division error")
	}
}

func TestSquareRoot(t *testing.T) {
	r, e := SquareRoot(25)
	if e != nil || r != 5 {
		t.Error("Square root failed")
	}
}

func TestNegativeRoot(t *testing.T) {
	_, e := SquareRoot(-9)
	if e == nil {
		t.Error("Expected negative root error")
	}
}

func TestEmptyInput(t *testing.T) {
	if CheckInput("") == nil {
		t.Error("Expected empty input error")
	}
}

func TestValidInput(t *testing.T) {
	if CheckInput("Anik") != nil {
		t.Error("Valid input failed")
	}
}
