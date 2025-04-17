package schwarz.it.kotlin.workshop.basic.syntax

// Use `when` that takes an integer `day` and prints the name of the day of the week, else `Invalid day number`
fun dayOfWeek(day: Int) {
}

private fun main() {
    for (day in 0..8) {
        println("Day $day:")
        dayOfWeek(day)
    }
}
