package schwarz.it.kotlin.workshop.basic.functional

// Fill `TODO()` so that `operation` is applied to `a` and `b`
fun operate(
    a: Int,
    b: Int,
    operation: (Int, Int) -> Int,
): Int = TODO()

private fun main() {
    // Fill `TODO()` should  be a lambda that adds two numbers
    val sum = operate(5, 3, TODO())
    println("Sum: $sum") // Output: Sum : 8

    // Fill `TODO()` should  be a lambda that multiples two numbers
    val multiply = operate(5, 3, TODO())
    println("Multiply: $multiply") // Output: Multiply : 15
}
