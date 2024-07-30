package schwarz.it.kotlin.workshop.web.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDetailsDTO(
    val description: String,
    val currentPrice: Double? = null,
    val validFrom: String? = null,
    val validTo: String? = null,
)
