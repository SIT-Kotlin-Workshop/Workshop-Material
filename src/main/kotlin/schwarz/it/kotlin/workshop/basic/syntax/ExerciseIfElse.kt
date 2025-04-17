package schwarz.it.kotlin.workshop.basic.syntax

// Use `if-else` statements to categorize the score into grades:
// A: 90-100
// B: 80-89
// C: 70-79
// D: 60-69
// F: Below 60
fun printGrades(score: Int) {
}

private fun main() {
    for (score in 10..100 step 10) {
        println("Score $score")
        printGrades(score)
    }
}
