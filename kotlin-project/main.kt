fun main() {

    println("Hello Kotlin!!!")

    println("\n--- Employee Bonus Calculation ---")

    print("Enter Department Code (IT/HR/SALES): ")
    val dept_code = readLine()!!.uppercase()

    print("Enter Years of Experience: ")
    val years = readLine()!!.toInt()

    print("Enter Employee Salary: ")
    val salary = readLine()!!.toDouble()

    val bonusPercentage = when {
        dept_code == "IT" && years > 5 -> 20
        dept_code == "HR" && years > 3 -> 15
        dept_code == "SALES" && years > 4 -> 18
        else -> 10
    }

    val bonus = salary * bonusPercentage / 100

    println("Department: $dept_code")
    println("Experience: $years years")
    println("Salary: $salary")
    println("Bonus Percentage: $bonusPercentage%")
    println("Bonus Amount: $bonus")

    // 3. Average of Individual Digits
    println("\n--- Average of Individual Digits ---")

    print("Enter an integer: ")
    val number = readLine()!!.toLong()

    var n = kotlin.math.abs(number)
    var sum = 0
    var count = 0

    if (n == 0L) {
        count = 1
    } else {
        while (n > 0) {
            val digit = (n % 10).toInt()
            sum += digit
            count++
            n /= 10
        }
    }

    val average = sum.toDouble() / count

    println("Sum of digits: $sum")
    println("Number of digits: $count")
    println("Average of digits: $average")

    println("\nProgram Completed Successfully!")
}