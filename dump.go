package main
import "fmt"

type Student struct {
	name string
	Age  int
	marks float64
}

func (s *Student) bonus(m float64){
	s.marks += m
}
func main() {
	s1 := Student{
		name:"anik",
		Age: 23,
		marks: 90.8,
	}
	fmt.Println("marks before bonus:", s1.marks)
	s1.bonus(5.2)
	fmt.Println(s1.marks)
}