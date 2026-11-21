
fun student(name: String = "Anik", age: Int = 21) {
    println("Name: $name")
    println("Age: $age")
}

// 3. Function with Vararg
fun printNumbers(vararg numbers: Int) {
    println("Numbers:")
    for (num in numbers) {
        println(num)
    }
}

// 4. Higher-Order Function
fun calculate(numbers: List<Int>, operation: (Int) -> Int) {
    val result = numbers.map(operation)
    println("Result: $result")
}

fun main() {

    // 1. Default Parameters
    println("--- Default Parameters ---")

    student()
    
    println("\nWith Arguments:")
    student("Rahul", 22)

    // 2. Named Arguments
    println("\n--- Named Arguments ---")

    student(age = 23, name = "Amit")

    // 3. Vararg Function
    println("\n--- Vararg Function ---")

    printNumbers(10, 20, 30, 40, 50)

    // 4. Higher-Order Function
    println("\n--- Higher-Order Function ---")

    val numbers = listOf(1, 2, 3, 4, 5)

    println("Original List: $numbers")

    // Lambda to calculate square
    calculate(numbers) { it * it }

    println("\nProgram Completed Successfully!")
}
