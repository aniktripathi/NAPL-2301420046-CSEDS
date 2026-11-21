class Student(
    var rollNo: Int,
    var name: String,
    var course: String,
    var percentage: Double
) {

    // Function to accept or update student details
    fun acceptData() {
        print("Enter Roll Number: ")
        rollNo = readLine()!!.toInt()

        print("Enter Student Name: ")
        name = readLine()!!

        print("Enter Course: ")
        course = readLine()!!

        print("Enter Percentage: ")
        percentage = readLine()!!.toDouble()
    }

    // Function to display student details
    fun displayData() {
        println("\n---------- Student Details ----------")
        println("Roll Number : $rollNo")
        println("Name        : $name")
        println("Course      : $course")
        println("Percentage  : $percentage%")
        println("-------------------------------------")
    }
}

fun main() {

    // Creating two Student objects using primary constructor
    val student1 = Student(0, "", "", 0.0)
    val student2 = Student(0, "", "", 0.0)

    println("Enter Details of Student 1")
    student1.acceptData()
    student1.displayData()

    println("\nEnter Details of Student 2")
    student2.acceptData()
    student2.displayData()
}