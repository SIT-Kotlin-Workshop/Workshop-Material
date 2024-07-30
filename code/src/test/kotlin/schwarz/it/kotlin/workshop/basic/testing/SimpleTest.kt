package schwarz.it.kotlin.workshop.basic.testing

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import java.util.Locale

class SimpleTest : AnnotationSpec() {
    @Test
    fun `simple assert - true`() {
        // Built-in assertions
        // Only enabled with special flag
        assert("kotlin".uppercase(Locale.GERMAN) == "kotlin".uppercase(Locale.GERMAN))

        // Using Kotest testing framework
        ("kotlin".uppercase(Locale.GERMAN) == "kotlin".uppercase(Locale.ENGLISH)) shouldBe true
    }

    @Test
    fun `simple assert - false`() {
        ("kotlin".uppercase(Locale.of("tr", "TR")) == "kotlin".uppercase(Locale.ENGLISH)) shouldBe true
    }

    @Test
    fun `better assert - false`() {
        "kotlin".uppercase(Locale.of("tr", "TR")) shouldBe "kotlin".uppercase(Locale.ENGLISH)
    }

    @Test
    fun `another test`() {
        val mascots = listOf("Duke", "Ferris", "Kodee", "Gopher")
        "kodee" shouldBeIn mascots.map { it.lowercase() }
    }
}
