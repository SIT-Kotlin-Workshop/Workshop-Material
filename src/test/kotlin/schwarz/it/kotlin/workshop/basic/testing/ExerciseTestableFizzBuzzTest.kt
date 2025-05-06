package schwarz.it.kotlin.workshop.basic.testing

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

/**
 * Add some tests for the [fizzbuzz] function here.
 */
class ExerciseTestableFizzBuzzTest {
    @Test
    fun `fizzbuzz test`() {
        // Replace me by your tests
        assertThat(fizzbuzz(1)).isEqualTo("TODO")

        // TODO: Remove from published source
        assertThat(fizzbuzz(1))
            .isEqualTo("1")
        assertThat(fizzbuzz(2))
            .isEqualTo("2")
        assertThat(fizzbuzz(3))
            .isEqualTo("Fizz")
        assertThat(fizzbuzz(4))
            .isEqualTo("4")
        assertThat(fizzbuzz(5))
            .isEqualTo("Buzz")
        assertThat(fizzbuzz(15))
            .isEqualTo("FizzBuzz")
        assertThat(fizzbuzz(31))
            .isEqualTo("31")
    }
}
