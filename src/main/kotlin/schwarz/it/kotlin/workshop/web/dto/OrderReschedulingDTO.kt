package schwarz.it.kotlin.workshop.web.dto

import kotlinx.serialization.Serializable
import java.time.DayOfWeek

@Serializable
data class OrderReschedulingDTO(
    val hour: Long,
    val deliveryDay: DayOfWeek,
)
