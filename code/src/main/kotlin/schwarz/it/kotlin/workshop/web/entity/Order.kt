package schwarz.it.kotlin.workshop.web.entity

import java.time.LocalDateTime

enum class Status {
    OPEN,
    IN_DELIVERY,
}

data class Order(
    val cost: Long,
    val status: Status,
    val estimatedDeliveryDate: LocalDateTime,
)
