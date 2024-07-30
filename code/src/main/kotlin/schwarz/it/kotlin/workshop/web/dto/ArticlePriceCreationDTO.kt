package schwarz.it.kotlin.workshop.web.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticlePriceCreationDTO(
    val price: Double,
    val startDate: String,
    val endDate: String,
)
