package schwarz.it.kotlin.workshop.basic.syntax

data class CartItem(val name: String, val quantity: Int, val price: Double)

private fun main() {

    immutableExampleForCartItem()
    println(calculateTotalWithTax())
    println(combinationOfHigherOrderFunctions(listOf(5.99, 12.39, 19.99, 8.59, 3.45), 1.1, 5.0))
    println(imperativeEvenDouble(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
    println(functionalEvenDouble(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
}

fun calculateTotalWithTax(): Double {

    val cartPrices = listOf(5.99, 12.39, 19.99, 8.59, 3.45)

    // Filter out cheap items
    val filteredPrices = cartPrices.filter { it >= 5.0 }

    // add tax to the filtered prices
    val taxedPrices = filteredPrices.map { it * 1.1 }

    // calculate the total
    return taxedPrices.fold(0.0) { acc, value -> acc + value }
}

fun combinationOfHigherOrderFunctions(cartPrices: List<Double>, taxRate: Double, minPrice: Double): Double =
    cartPrices
        .filter { it >= minPrice }
        .map { it * taxRate }
        .fold(0.0) { acc, value -> acc + value }


fun immutableExampleForCartItem() {

    val originalItem = CartItem("T-Shirt", 1, 5.99)
    val discountedItem = originalItem.copy(price = 4.99)

    println(discountedItem)
}

fun imperativeEvenDouble(input: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (number in input) {
        if (number % 2 == 0) {
            result.add(number * 2)
        }
    }
    return result
}

fun functionalEvenDouble(input: List<Int>): List<Int> {
    return input.filter { it % 2 == 0 }
        .map { it * 2 }
}
