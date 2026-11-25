// Student Course Registration System

// 1. DOMAIN MODEL

data class Student(
    val id: Int,
    val name: String,
    val email: String? = null
)

data class Course(
    val code: String,
    val title: String,
    val fee: Double,
    val instructor: String? = null
)

data class Registration(
    val student: Student,
    val course: Course,
    val marks: Int
)


// 2. EXTENSION FUNCTIONS

// Extension function 1: Check student result
fun Registration.getResult(): String {
    return if (marks >= 40) "PASS" else "FAIL"
}

// Extension function 2: Calculate grade
fun Registration.getGrade(): String {
    return when {
        marks >= 90 -> "A+"
        marks >= 80 -> "A"
        marks >= 70 -> "B"
        marks >= 60 -> "C"
        marks >= 40 -> "D"
        else -> "F"
    }
}


// 3. DSL FOR COURSE REGISTRATION

class RegistrationDSL {

    val students = mutableListOf<Student>()
    val courses = mutableListOf<Course>()
    val registrations = mutableListOf<Registration>()

    fun student(
        id: Int,
        name: String,
        email: String? = null
    ) {
        students.add(Student(id, name, email))
    }

    fun course(
        code: String,
        title: String,
        fee: Double,
        instructor: String? = null
    ) {
        courses.add(Course(code, title, fee, instructor))
    }

    fun register(
        studentId: Int,
        courseCode: String,
        marks: Int
    ) {
        val student = students.find { it.id == studentId }
        val course = courses.find { it.code == courseCode }

        if (student != null && course != null && marks in 0..100) {
            registrations.add(
                Registration(student, course, marks)
            )
        } else {
            println("Invalid registration: $studentId, $courseCode")
        }
    }
}

// DSL builder function
fun registrationSystem(
    block: RegistrationDSL.() -> Unit
): RegistrationDSL {

    val system = RegistrationDSL()
    system.block()
    return system
}


// 4. MAIN PROGRAM

fun main() {

    // Configure system using DSL

    val system = registrationSystem {

        student(1, "Anik", "anik@gmail.com")
        student(2, "Rahul", "rahul@gmail.com")
        student(3, "Aman")

        course(101.toString(), "Kotlin Programming", 3000.0, "Mr. Raj")
        course(102.toString(), "Java Programming", 2500.0, "Mr. Amit")
        course(103.toString(), "Python Programming", 2000.0)

        register(1, "101", 92)
        register(2, "101", 75)
        register(3, "102", 35)
        register(1, "103", 85)
    }


    val registrations = system.registrations


    // FUNCTIONAL COLLECTION OPERATIONS

    // MAP: Get student names

    println("\n--- STUDENT NAMES ---")

    val names = registrations.map {
        it.student.name
    }

    println(names)


    // FILTER: Find passed students

    println("\n--- PASSED STUDENTS ---")

    val passed = registrations.filter {
        it.marks >= 40
    }

    passed.forEach {
        println("${it.student.name} - ${it.getResult()}")
    }


    // REDUCE: Calculate total marks

    println("\n--- TOTAL MARKS ---")

    val totalMarks = registrations.reduce { total, item ->
        Registration(
            item.student,
            item.course,
            total.marks + item.marks
        )
    }.marks

    println("Total Marks: $totalMarks")


    // GROUPBY: Group registrations by course

    println("\n--- COURSE-WISE STUDENTS ---")

    val grouped = registrations.groupBy {
        it.course.title
    }

    grouped.forEach { (course, students) ->

        println("\nCourse: $course")

        students.forEach {
            println("${it.student.name} - ${it.marks}")
        }
    }


    // EXTENSION FUNCTIONS

    println("\n--- RESULTS AND GRADES ---")

    registrations.forEach {

        println(
            "${it.student.name} | " +
            "${it.course.title} | " +
            "Marks: ${it.marks} | " +
            "Result: ${it.getResult()} | " +
            "Grade: ${it.getGrade()}"
        )
    }


    // NULL SAFETY

    println("\n--- NULL SAFE INFORMATION ---")

    system.students.forEach {

        println(
            "Student: ${it.name}, " +
            "Email: ${it.email ?: "Not Provided"}"
        )
    }


    // FINAL REPORT DSL

    println("\n--- REGISTRATION REPORT ---")

    val report = buildString {

        appendLine("STUDENT COURSE REGISTRATION REPORT")
        appendLine("----------------------------------")

        appendLine("Total Students: ${system.students.size}")
        appendLine("Total Courses: ${system.courses.size}")
        appendLine("Total Registrations: ${registrations.size}")

        appendLine("Passed: ${passed.size}")
        appendLine("Failed: ${registrations.size - passed.size}")

        appendLine("Total Marks: $totalMarks")
    }

    print(report)
}