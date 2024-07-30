package schwarz.it.kotlin.workshop.basic.functional

// 1. Fill `TODO()` should apply `operation` to the list
fun applyOperationToList(
    numbers: List<Int>,
    operation: (Int) -> Int,
): List<Int> = numbers.map(TODO())

private fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)

    // 2. Fill `TODO()` should be a lambda that doubles a number
    val doubled = applyOperationToList(numbers, TODO())
    println("Doubled: $doubled") // Output: Doubled: [2, 4, 6, 8, 10]

    // 2. Fill `TODO()` should be a lambda that squares a number
    val squared = applyOperationToList(numbers, TODO())
    println("Squared: $squared") // Output: Squared: [1, 4, 9, 16, 25]

    // 3. Use `filter` from the standard library to get a list of even numbers in `numbers`. Print it.
}
