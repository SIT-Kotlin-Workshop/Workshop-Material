package schwarz.it.kotlin.workshop.basic.testing

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.DayOfWeek
import java.time.LocalDate

class WithDependencyTest : AnnotationSpec() {
    private val mockedDateService = mockk<DateService>()

    private val workingInfoService =
        WorkingInfoService(
            dateService = mockedDateService,
        )

    @Test
    fun `getWorkingsHours - weekend`() {
        workingInfoService.getWorkingsHours(DayOfWeek.SUNDAY).isEmpty() shouldBe true
    }

    @Test
    fun `getWorkingsHours - weekday`() {
        workingInfoService.getWorkingsHours(DayOfWeek.MONDAY).first shouldBe 9
        workingInfoService.getWorkingsHours(DayOfWeek.MONDAY).last shouldBe 17
    }

    @Test
    fun `getWorkingHoursToday - weekend`() {
        every { mockedDateService.getToday() } returns LocalDate.of(2024, 7, 6)

        workingInfoService.getWorkingHoursToday() shouldHaveSize 0
    }
}
