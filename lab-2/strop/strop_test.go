package strop

import "testing"

func TestReverse(t *testing.T) {
	tests := []struct {
		name  string
		input string
		want  string
	}{
		{name: "word", input: "hello", want: "olleh"},
		{name: "unicode", input: "Go 😊", want: "😊 oG"},
		{name: "empty", input: "", want: ""},
	}

	for _, test := range tests {
		t.Run(test.name, func(t *testing.T) {
			if got := Reverse(test.input); got != test.want {
				t.Fatalf("Reverse(%q) = %q, want %q", test.input, got, test.want)
			}
		})
	}
}

func TestCountVowels(t *testing.T) {
	tests := []struct {
		name  string
		input string
		want  int
	}{
		{name: "mixed case", input: "Hello World", want: 3},
		{name: "all vowels", input: "aeiouAEIOU", want: 10},
		{name: "no vowels", input: "rhythms", want: 0},
	}

	for _, test := range tests {
		t.Run(test.name, func(t *testing.T) {
			if got := CountVowels(test.input); got != test.want {
				t.Fatalf("CountVowels(%q) = %d, want %d", test.input, got, test.want)
			}
		})
	}
}
