package mathutil

import "testing"

func TestAdd(t *testing.T) {
	if got := Add(5, 10); got != 15 {
		t.Fatalf("Add(5, 10) = %d, want 15", got)
	}
}

func TestFact(t *testing.T) {
	tests := []struct {
		name  string
		input int
		want  int
	}{
		{name: "zero", input: 0, want: 1},
		{name: "positive", input: 5, want: 120},
		{name: "negative", input: -1, want: 0},
	}

	for _, test := range tests {
		t.Run(test.name, func(t *testing.T) {
			if got := Fact(test.input); got != test.want {
				t.Fatalf("Fact(%d) = %d, want %d", test.input, got, test.want)
			}
		})
	}
}

func TestPow(t *testing.T) {
	tests := []struct {
		name     string
		base     int
		exponent int
		want     int
	}{
		{name: "zero exponent", base: 7, exponent: 0, want: 1},
		{name: "positive exponent", base: 2, exponent: 4, want: 16},
		{name: "negative exponent", base: 2, exponent: -1, want: 0},
	}

	for _, test := range tests {
		t.Run(test.name, func(t *testing.T) {
			if got := Pow(test.base, test.exponent); got != test.want {
				t.Fatalf("Pow(%d, %d) = %d, want %d", test.base, test.exponent, got, test.want)
			}
		})
	}
}
