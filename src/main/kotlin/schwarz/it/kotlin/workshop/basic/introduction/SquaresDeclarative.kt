package schwarz.it.kotlin.workshop.basic.introduction

private fun firstHundredSquares(): List<Int> {
    val allNumbers = generateSequence(0) { it + 1 }

    val allSquares = allNumbers.map { it * it }

    val firstSquares = allSquares.take(101)

    return firstSquares.toList()
}

private fun main() {
    println(firstHundredSquares())
}
