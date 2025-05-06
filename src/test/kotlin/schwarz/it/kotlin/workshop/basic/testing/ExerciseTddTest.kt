package schwarz.it.kotlin.workshop.basic.testing

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class ExerciseTddTest {
    @Test
    fun `tests for the mysterious function`() {
        assertThat(mysteriousFunction(""))
            .isEqualTo("00000")

        assertThat(mysteriousFunction("schwarz group"))
            .isEqualTo("10011")

        assertThat(mysteriousFunction("kotlin"))
            .isEqualTo("00110")

        assertThat(mysteriousFunction("workshop"))
            .isEqualTo("00020")

        assertThat(mysteriousFunction("sky"))
            .isEqualTo("00000")
    }
}
