package strop

func CountVowels(value string) int {
	count := 0
	for _, character := range value {
		switch character {
		case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U':
			count++
		}
	}

	return count
}