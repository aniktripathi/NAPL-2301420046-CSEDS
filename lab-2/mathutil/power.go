package mathutil

func Pow(a, b int) int {
	if b < 0 {
		return 0
	}

	result := 1
	for exponent := 0; exponent < b; exponent++ {
		result *= a
	}

	return result
}
