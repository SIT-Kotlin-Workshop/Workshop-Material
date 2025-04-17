package schwarz.it.kotlin.workshop.web.dto

import kotlinx.serialization.Serializable
import java.time.DayOfWeek

@Serializable
data class OrderCreationDTO(
    val cost: Long,
    val deliveryDay: DayOfWeek,
)
