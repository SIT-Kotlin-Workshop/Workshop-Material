package schwarz.it.kotlin.workshop.basic.functional

// Define this function so that it returns the highest number in `list` that satisfies predicate
// (meaning predicate(number) == true)
fun highestSatisfying(
    list: List<Int>,
    predicate: (Int) -> Boolean,
): Int? {
    // TODO()

    return null // Placeholder return value for the solution
}

private fun main() {
    // Use the function to find the highest number that divides 1337 (not equal to 1337 itself)
    val number =
        highestSatisfying((1..<1337).toList()) {
            1337 % it == 0
        }
    println(number)
}
