package main

import "fmt"

func main() {


	students := []string{"Anik", "Rahul", "Priya"}

	fmt.Println("Initial Slice:", students)

// ADD
	students = append(students, "Aman")
	fmt.Println("After ADD:", students)

	index := 1
	students = append(students[:index], students[index+1:]...)
	fmt.Println("After REMOVE at index 1:", students)

// UPDATe
	students[1] = "Neha"
	fmt.Println("After UPDATE at index 1:", students)

	fmt.Println("\n===== MAP OPERATIONS =====")

	marks := map[string]int{
		"Maths":    85,
		"Science":  90,
		"English":  78,
	}

	fmt.Println("Initial Map:", marks)

	
	marks["Computer"] = 95
	fmt.Println("After INSERT:", marks)

	
	delete(marks, "English")
	fmt.Println("After DELETE:", marks)

	
	subject := "Maths"
	mark, exists := marks[subject]

	if exists {
		fmt.Println("LOOKUP:", subject, "=", mark)
	} else {
		fmt.Println("LOOKUP:", subject, "not found")
	}

	fmt.Println("Map After LOOKUP:", marks)
}