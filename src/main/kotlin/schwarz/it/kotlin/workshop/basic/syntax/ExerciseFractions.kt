package schwarz.it.kotlin.workshop.basic.syntax

import kotlin.math.absoluteValue
import kotlin.math.sign

/**
 * Implement this function so that it takes a fraction
 * ```
 * enumerator
 * ----------
 * denominator
 * ```
 * and simplifies it as much as possible.
 *
 * See the [main] for examples.
 *
 * Hint: You may need [Euclid's algorithm](https://en.wikipedia.org/wiki/Euclidean_algorithm#Implementations)
 */
fun simplify(inputFraction: Pair<Int, Int>): Pair<Int, Int> {
    val (enumerator, denominator) = inputFraction

    // Put your code here

    // This is how to construct/return a pair:
    return 1 to 4
}

/**
 * Run to test your function
 */
private fun main() {
    val tests =
        listOf(
            (1 to 1) to (1 to 1),
            (1 to 2) to (1 to 2),
            (2 to 4) to (1 to 2),
            (2 to 2) to (1 to 1),
            (-3 to -4) to (3 to 4),
            (-3 to 5) to (-3 to 5),
            (3 to -5) to (-3 to 5),
            (0 to 3) to (0 to 1),
            (0 to -3) to (0 to 1),
            (-21 to -91) to (3 to 13),
        )

    for (test in tests) {
        val (input, expectedOutput) = test
        val actualOutput = simplify(input)
        val outcome = if (actualOutput == expectedOutput) "PASS" else "FAIL"
        println(
            "- Test $outcome: simplify(${input.first}/${input.second}) should be ${expectedOutput.first}/${expectedOutput.second} and is ${actualOutput.first}/${actualOutput.second}",
        )
    }
}
