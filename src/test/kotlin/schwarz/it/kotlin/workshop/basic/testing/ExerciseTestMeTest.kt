package schwarz.it.kotlin.workshop.basic.testing

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isZero
import assertk.assertions.message
import org.junit.jupiter.api.Test

class ExerciseTestMeTest {
    // TODO: Remove from published source
    @Test
    fun `testMe - error on blank input`() {
        val exceptionOnEmptyString =
            assertFailure {
                testMe(listOf(""))
            }.message().isEqualTo("Input contains blank string")

        assertFailure {
            testMe(listOf(""))
        }.message().isEqualTo("Input contains blank string")

        assertFailure {
            testMe(listOf("Test", "    "))
        }.message().isEqualTo("Input contains blank string")
    }

    // TODO: Remove from published source
    @Test
    fun `testMe - non-palindromes`() {
        assertThat(testMe(emptyList()))
            .isZero()

        assertThat(testMe(listOf("Test")))
            .isZero()

        assertThat(testMe(listOf("Test", "toast")))
            .isZero()
    }

    // TODO: Remove from published source
    @Test
    fun `testMe - palindromes`() {
        // odd-length palindrome
        assertThat(testMe(listOf("level")))
            .isEqualTo(1)

        // even-length palindrome
        assertThat(testMe(listOf("anna")))
            .isEqualTo(1)

        // ignores capitalization
        assertThat(testMe(listOf("Anna")))
            .isEqualTo(1)
        assertThat(testMe(listOf("aNnA")))
            .isEqualTo(1)

        // trims
        assertThat(testMe(listOf("  radar   ")))
            .isEqualTo(1)

        // multiple
        assertThat(testMe(listOf("wow", "noon", "mom")))
            .isEqualTo(3)
    }

    // TODO: Remove from published source
    @Test
    fun `testMe - mixed input`() {
        assertThat(testMe(listOf("rotor", "motor")))
            .isEqualTo(1)

        assertThat(testMe(listOf("sir", "madam", "racecar")))
            .isEqualTo(2)
    }
}
