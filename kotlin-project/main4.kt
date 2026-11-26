// Experiment 29: Collections and Functional Transformations

// Student class
data class Student(
    val name: String,
    val percentage: Double,
    val course: String
)

fun main() {

    // 1. LIST COLLECTIONS

    println("----- LIST COLLECTIONS -----")

    // Read-only List
    val list = listOf(10, 20, 30, 40)
    println("Read-only List: $list")
    println("Contains 20: ${list.contains(20)}")
    println("Get element: ${list[1]}")

    // Mutable List
    val mutableList = mutableListOf(10, 20, 30)
    mutableList.add(40)
    mutableList.remove(20)
    println("Mutable List: $mutableList")


    // 2. SET COLLECTIONS

    println("\n----- SET COLLECTIONS -----")

    // Read-only Set
    val set = setOf(10, 20, 30, 40)
    println("Read-only Set: $set")
    println("Contains 20: ${set.contains(20)}")

    // Mutable Set
    val mutableSet = mutableSetOf(10, 20, 30)
    mutableSet.add(40)
    mutableSet.remove(20)
    println("Mutable Set: $mutableSet")


    // 3. MAP COLLECTIONS

    println("\n----- MAP COLLECTIONS -----")

    // Read-only Map
    val map = mapOf(
        1 to "Anik",
        2 to "Rahul",
        3 to "Aman"
    )

    println("Read-only Map: $map")
    println("Get value: ${map[1]}")
    println("Contains key 2: ${map.containsKey(2)}")

    // Mutable Map
    val mutableMap = mutableMapOf(
        1 to "Anik",
        2 to "Rahul"
    )

    mutableMap[3] = "Aman"
    mutableMap.remove(2)

    println("Mutable Map: $mutableMap")


    // 4. FUNCTIONAL TRANSFORMATIONS

    println("\n----- FUNCTIONAL TRANSFORMATIONS -----")

    val numbers = listOf(1, 2, 3, 4, 5)

    // map: Square each number
    val squares = numbers.map { it * it }
    println("Original List: $numbers")
    println("Map (Squares): $squares")

    // filter: Select even numbers
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println("Filter (Even Numbers): $evenNumbers")

    // reduce: Calculate sum
    val sum = numbers.reduce { total, num ->
        total + num
    }
    println("Reduce (Sum): $sum")


    // 5. SORTING STUDENT OBJECTS

    println("\n----- STUDENT SORTING -----")

    val students = listOf(
        Student("Anik", 85.5, "CSE"),
        Student("Rahul", 78.0, "IT"),
        Student("Aman", 92.0, "CSE"),
        Student("Rohit", 88.0, "IT")
    )

    // Sort students by percentage
    val sortedStudents = students.sortedByDescending {
        it.percentage
    }

    println("Students Sorted by Percentage:")

    sortedStudents.forEach {
        println("${it.name} - ${it.percentage}% - ${it.course}")
    }


    // 6. GROUPING COLLECTIONS

    println("\n----- GROUP BY COURSE -----")

    val groupedStudents = students.groupBy {
        it.course
    }

    groupedStudents.forEach { (course, studentList) ->
        println("$course: ${studentList.map { it.name }}")
    }
}