package schwarz.it.kotlin.workshop.basic.functional

private fun main() {
    val numbers = (1..20).toList()

    // Example: Printing each number
    numbers.forEach {
        println(it)
    }

    // 1. Use a higher-order function to obtain the first 20 square numbers

    val squares = listOf<Int>() // TODO("Write the solution using map")

    println(squares)

    // 2. Use higher-order functions to find the sum of all squares of numbers in 1..100 that are divisible by 3.

    val sum = 0 // TODO("Write the solution using filter and map")

    println(sum)

    val lines = LOREM_IPSUM.trimIndent().lines()

    // 3. Use higher-order functions to find the line in `lines` that contains the letter `o` the most often

    val line = "" // TODO("Write the solution using maxBy")

    println(line)

    // 4. Use the `fold` higher-order function to count the total number of `o`s in lines.

    val count = 0 // TODO("Write the solution using fold")

    println(count)
}

private const val LOREM_IPSUM =
    """
    Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.
    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi consequat.
    Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
    Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. 
    """
