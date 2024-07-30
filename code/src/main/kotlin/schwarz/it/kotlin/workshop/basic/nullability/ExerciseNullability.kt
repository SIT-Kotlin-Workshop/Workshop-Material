@file:Suppress("ktlint:standard:filename")

package schwarz.it.kotlin.workshop.basic.nullability

import java.time.LocalDate

data class Friend(
    val name: String,
    val friendSince: LocalDate?,
)

private fun main() {
    val friends =
        listOf(
            Friend("Adam", LocalDate.of(2023, 5, 2)),
            Friend("Eve", null),
        )

    // 1. Iterate over the `friends` and print information about each friend in a separate line
    //    e.g. "Adam (friends since 2023-05-02)"
    //    When the date is `null`, print "UNKNOWN" instead.

    val moreFriends =
        listOf(
            Friend("Alice", LocalDate.of(1996, 8, 13)),
            Friend("Bob", null),
            null,
        )

    // 2. Do the same as before for `moreFriends`.
    //    Println "UNKNOWN FRIEND" when the friend itself is `null`.
    //    You can try the various methods of handling `null` that you have seen on the slides.
}
