package schwarz.it.kotlin.workshop.web.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticleCreationDTO(
    val name: String,
    val description: String,
)
