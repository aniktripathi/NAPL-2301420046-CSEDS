package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type Person struct {
	Name   string
	Age    int
	Job    string
	Salary float64
}

func (p *Person) ReadData(reader *bufio.Reader) error {
	readLine := func(prompt string) (string, error) {
		fmt.Print(prompt)
		input, err := reader.ReadString('\n')
		if err != nil && len(input) == 0 {
			return "", err
		}
		return strings.TrimSpace(input), nil
	}

	var err error
	if p.Name, err = readLine("Enter name: "); err != nil {
		return fmt.Errorf("read name: %w", err)
	}

	age, err := readLine("Enter age: ")
	if err != nil {
		return fmt.Errorf("read age: %w", err)
	}
	if p.Age, err = strconv.Atoi(age); err != nil {
		return fmt.Errorf("invalid age %q: %w", age, err)
	}

	if p.Job, err = readLine("Enter job: "); err != nil {
		return fmt.Errorf("read job: %w", err)
	}

	salary, err := readLine("Enter salary: ")
	if err != nil {
		return fmt.Errorf("read salary: %w", err)
	}
	if p.Salary, err = strconv.ParseFloat(salary, 64); err != nil {
		return fmt.Errorf("invalid salary %q: %w", salary, err)
	}

	return nil
}

func (p Person) PrintData() {
	fmt.Printf("Name: %s\nAge: %d\nJob: %s\nSalary: %.2f\n", p.Name, p.Age, p.Job, p.Salary)
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	people := []Person{{}, {}}

	for i := range people {
		fmt.Printf("\nEnter details for person %d:\n", i+1)
		if err := people[i].ReadData(reader); err != nil {
			fmt.Fprintln(os.Stderr, "Error:", err)
			os.Exit(1)
		}

		fmt.Printf("\nPerson %d details:\n", i+1)
		people[i].PrintData()
	}
}
