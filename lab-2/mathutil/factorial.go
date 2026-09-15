package mathutil

func Fact(a int) int {
	if a < 0 {
		return 0
	}

	result := 1
	for value := 2; value <= a; value++ {
		result *= value
	}

	return result
}
