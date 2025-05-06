package schwarz.it.kotlin.workshop.basic.testing

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import java.util.Locale

class ExamplesTest {
    @Test
    fun `simple assert - true`() {
        // Built-in assertions
        // Only enabled with special flag
        assert("kotlin".uppercase(Locale.GERMAN) == "kotlin".uppercase(Locale.ENGLISH))

        // Using assertk
        assertThat("kotlin".uppercase(Locale.GERMAN) == "kotlin".uppercase(Locale.ENGLISH))
            .isTrue()
    }

    @Test
    fun `simple assert - false`() {
        assertThat("kotlin".uppercase(Locale.of("tr", "TR")) == "kotlin".uppercase(Locale.ENGLISH))
            .isTrue()
    }

    @Test
    fun `better assert - false`() {
        assertThat("kotlin".uppercase(Locale.of("tr", "TR")))
            .isEqualTo("kotlin".uppercase(Locale.ENGLISH))
    }

    @Test
    fun `another test`() {
        val mascots = listOf("Duke", "Ferris", "Kodee", "Gopher")
        assertThat(mascots).contains("Kodee")
    }
}
