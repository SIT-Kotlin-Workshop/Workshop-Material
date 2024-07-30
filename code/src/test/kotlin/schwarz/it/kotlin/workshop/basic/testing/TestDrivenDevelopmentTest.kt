package schwarz.it.kotlin.workshop.basic.testing

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class TestDrivenDevelopmentTest : AnnotationSpec() {
    @Test
    fun `mysteriousFunction - error on negative input`() {
        val exception =
            shouldThrow<Exception> {
                mysteriousFunction(-1)
            }
        exception.message shouldBe "Negative input"
    }

    @Test
    fun `mysteriousFunction - computes list of numbers`() {
        mysteriousFunction(0).shouldBeEmpty()
        mysteriousFunction(1).shouldBeEmpty()
        mysteriousFunction(2).shouldBeEmpty()
        mysteriousFunction(3).shouldContainExactly(2)
        mysteriousFunction(4).shouldContainExactly(2, 3)
        mysteriousFunction(5).shouldContainExactly(2, 3)
        mysteriousFunction(6).shouldContainExactly(2, 3, 5)
        mysteriousFunction(10).shouldContainExactly(2, 3, 5, 7)
        mysteriousFunction(20).shouldContainExactly(2, 3, 5, 7, 11, 13, 17, 19)
    }
}
