package schwarz.it.kotlin.workshop.basic.syntax

// Print all even numbers smaller than or equal to `n`
fun printEvenNumbers(n: Int) {
    for (i in 0..n) {
        if (i % 2 == 0) {
            println(i)
        }
    }
}

private fun main() {
    printEvenNumbers(21)
}
