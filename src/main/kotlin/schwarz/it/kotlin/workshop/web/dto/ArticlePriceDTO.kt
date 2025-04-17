package schwarz.it.kotlin.workshop.web.dto

import kotlinx.serialization.Serializable
import schwarz.it.kotlin.workshop.web.entity.ArticlePrice

@Serializable
data class ArticlePriceDTO(
    val price: Double,
    val startDate: String,
    val endDate: String,
)

fun ArticlePrice.toArticlePriceDTO() =
    ArticlePriceDTO(
        price = this.price,
        startDate = this.startDate.toString(),
        endDate = this.endDate.toString(),
    )
