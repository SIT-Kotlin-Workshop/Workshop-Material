package schwarz.it.kotlin.workshop.basic.testing

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.message
import org.junit.jupiter.api.Test

class ExerciseTddAdvancedTest {
    @Test
    fun `mysteriousFunction - error on negative input`() {
        assertFailure {
            anotherMysteriousFunction(-1)
        }.message().isEqualTo("Negative input")
    }

    @Test
    fun `mysteriousFunction - computes some list of numbers`() {
        assertThat(anotherMysteriousFunction(0))
            .isEmpty()
        assertThat(anotherMysteriousFunction(1))
            .isEmpty()
        assertThat(anotherMysteriousFunction(2))
            .isEmpty()
        assertThat(anotherMysteriousFunction(3))
            .containsExactly(2)
        assertThat(anotherMysteriousFunction(4))
            .containsExactly(2, 3)
        assertThat(anotherMysteriousFunction(5))
            .containsExactly(2, 3)
        assertThat(anotherMysteriousFunction(6))
            .containsExactly(2, 3, 5)
        assertThat(anotherMysteriousFunction(10))
            .containsExactly(2, 3, 5, 7)
        assertThat(anotherMysteriousFunction(20))
            .containsExactly(2, 3, 5, 7, 11, 13, 17, 19)
    }
}
