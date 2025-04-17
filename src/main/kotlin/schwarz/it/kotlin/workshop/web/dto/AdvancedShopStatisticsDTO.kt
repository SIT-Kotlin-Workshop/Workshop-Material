package schwarz.it.kotlin.workshop.web.dto

import kotlinx.serialization.Serializable

@Serializable
data class AdvancedShopStatisticsDTO(
    val timestamp: String,
    val totalOrders: Long,
    val ordersInDelivery: Long,
    val orders: List<Long>,
)
