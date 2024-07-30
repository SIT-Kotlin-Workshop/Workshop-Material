package schwarz.it.kotlin.workshop.web.util

import java.time.DayOfWeek
import java.time.LocalDateTime

/**
 * Adds days to `this` date until the specified [day] is reached.
 */
tailrec fun LocalDateTime.next(day: DayOfWeek): LocalDateTime =
    if (this.dayOfWeek == day) {
        this
    } else {
        this.plusDays(1).next(day)
    }
