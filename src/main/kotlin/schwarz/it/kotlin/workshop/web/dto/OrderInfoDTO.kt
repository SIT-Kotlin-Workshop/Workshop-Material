package schwarz.it.kotlin.workshop.web.dto

import kotlinx.serialization.Serializable
import schwarz.it.kotlin.workshop.web.entity.Order

@Serializable
data class OrderInfoDTO(
    val cost: Long,
    val status: String,
    val estimatedDeliveryDate: String,
)

fun Order.toOrderInfoDTO() =
    OrderInfoDTO(
        cost = cost,
        status = status.name,
        estimatedDeliveryDate = estimatedDeliveryDate.toString(),
    )
