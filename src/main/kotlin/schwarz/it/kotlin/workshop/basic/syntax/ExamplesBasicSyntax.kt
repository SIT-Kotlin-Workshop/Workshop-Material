package schwarz.it.kotlin.workshop.basic.syntax

private fun main() {
    println(calculateDiscountedPrice(510.5, 15.0))
    println(calculateDiscountedPriceExpression(510.5, 15.0))

    examplesIfThenElse()
    examplesForLoop()
    exampleWhileLoop()
    exampleDoWhileLoop()
}

fun calculateDiscountedPrice(
    price: Double,
    discountPercent: Double,
): Double {
    val discount = price * (discountPercent / 100)
    return price - discount
}

fun calculateDiscountedPriceExpression(
    price: Double,
    discountPercent: Double,
) = price - (price * (discountPercent / 100))

private enum class Enum {
    ONE,
    TWO,
    THREE,
}

fun examplesWhen() {
    val number = 5

    when {
        number == 1 -> println("One")
        number == 2 -> println("Two")
    }

    when (number) {
        1 -> println("One")
        2 -> println("Two")
    }

    val output =
        when (number) {
            1 -> "One"
            2 -> "Two"
            else -> "Many"
        }
    println(output)

    val number2 = Enum.THREE
    val output2 =
        when (number2) {
            Enum.ONE -> "One"
            Enum.TWO -> "Two"
            Enum.THREE -> "Three"
        }
}

fun examplesIfThenElse() {
    val number = 5
    if (number > 2) {
        println("Number is greater than 2!")
    }

    if (number > 3) {
        println("Number is also greater than 3!")
    } else {
        println("Number is less than 3!")
    }

    val output =
        if (number > 4) {
            "Number is greater than 4!"
        } else {
            "Number is less than 4!"
        }

    println(output)
}

fun exampleWhileLoop() {
    println("Countdown:")
    var number = 10

    while (number > 0) {
        println("$number...")
        number--
    }

    println("Go!")
}

fun exampleDoWhileLoop() {
    do {
        println("Enter \"break\" to terminate loop!")
        print("Input: ")
        val input = readln()
    } while (input != "break")
    println("Exited loop!")
}

fun examplesForLoop() {
    println("Closed end range:")
    for (i in 1..5) {
        println(i)
    }

    println("Open end range:")
    for (i in 1..<5) {
        println(i)
    }

    println("Reverse with step:")
    for (i in 10 downTo 1 step 2) {
        println(i)
    }

    val mascots = listOf("Rust: Ferris", "Go: Gopher", "Java: Duke", "Kotlin: Kodee")
    for (mascot in mascots) {
        println(mascot)
    }
}
