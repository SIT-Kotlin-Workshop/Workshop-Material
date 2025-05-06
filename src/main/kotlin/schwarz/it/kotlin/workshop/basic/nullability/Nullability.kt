@file:Suppress("ktlint:standard:filename")

package schwarz.it.kotlin.workshop.basic.nullability

import java.time.LocalDate

data class Person(
    val name: String,
    val birthday: LocalDate,
) {
    fun format(): String = "$name ($birthday)"
}

private fun main() {
    // Illegal in Kotlin
    // val person: Person = null
    val person = Person("James", LocalDate.of(1955, 5, 19))

    // Property access would crash with NPE if person is `null`
    println(person.name)

    // Function call would crash will NPE if person is `null`
    println(person.format())
}
