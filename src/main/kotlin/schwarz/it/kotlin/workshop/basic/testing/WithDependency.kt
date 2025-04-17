package schwarz.it.kotlin.workshop.basic.testing

import java.time.DayOfWeek
import java.time.DayOfWeek.FRIDAY
import java.time.DayOfWeek.MONDAY
import java.time.DayOfWeek.SATURDAY
import java.time.DayOfWeek.SUNDAY
import java.time.DayOfWeek.THURSDAY
import java.time.DayOfWeek.TUESDAY
import java.time.DayOfWeek.WEDNESDAY
import java.time.LocalDate
import java.time.LocalDateTime

class DateService {
    fun getToday(): LocalDate = LocalDate.now()

    fun getNow(): LocalDateTime = LocalDateTime.now()
}

class WorkingInfoService(
    private val dateService: DateService,
) {
    fun getWorkingsHours(day: DayOfWeek) =
        when (day) {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> 9..17
            SATURDAY, SUNDAY -> IntRange.EMPTY
        }

    fun getWorkingHoursToday() = getWorkingsHours(dateService.getToday().dayOfWeek)

    fun getRemainingWorkingHoursToday(): Int {
        val now = dateService.getNow()
        val range = getWorkingsHours(now.dayOfWeek)

        return when {
            range.isEmpty() -> 0
            now.hour <= range.first -> range.last - range.first
            now.hour >= range.last -> 0
            else -> range.last - now.hour
        }
    }
}
