// Experiment 28: Null Safety in Kotlin

fun main() {

    // 1. Nullable and Non-nullable Variables

    println("----- NULLABLE AND NON-NULLABLE -----")

    var name: String = "Anik"
    var nickname: String? = null

    println("Non-nullable Name: $name")
    println("Nullable Nickname: $nickname")

    // name = null  // Compile-time error
    nickname = "AK" // Allowed

    println("Updated Nickname: $nickname")


    // 2. Safe Call Operator (?.)

    println("\n----- SAFE CALL OPERATOR -----")

    var city: String? = "Faridabad"

    println("City Length: ${city?.length}")

    city = null

    println("City Length: ${city?.length}")


    // 3. Elvis Operator (?:)

    println("\n----- ELVIS OPERATOR -----")

    var course: String? = null

    val result = course ?: "Computer Science"

    println("Course: $result")

    course = "Data Science"

    val result2 = course ?: "Computer Science"

    println("Updated Course: $result2")


    // 4. Not-null Assertion Operator (!!)

    println("\n----- NOT-NULL ASSERTION OPERATOR -----")

    var message: String? = null

    try {
        println(message!!.length)
    } catch (e: NullPointerException) {
        println("Exception caught: ${e.message}")
    }

    println("Program continues normally.")
}