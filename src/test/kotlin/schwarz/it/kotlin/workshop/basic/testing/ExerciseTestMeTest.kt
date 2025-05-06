package schwarz.it.kotlin.workshop.basic.testing

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class ExerciseTestMeTest {
    @Test
    fun `testMe - test 1`() {
        // Replace by your tests
        assertThat(testMe(listOf()))
            .isEqualTo(0)
    }
}
