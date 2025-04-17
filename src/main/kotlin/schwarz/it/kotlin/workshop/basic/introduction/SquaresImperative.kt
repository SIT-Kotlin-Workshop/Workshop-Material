package schwarz.it.kotlin.workshop.basic.introduction

private fun firstHundredSquares(): List<Int> {
    val squares = mutableListOf<Int>()

    for (i in 0..100) {
        squares.add(i * i)
    }

    return squares
}

private fun main() {
    println(firstHundredSquares())
}
