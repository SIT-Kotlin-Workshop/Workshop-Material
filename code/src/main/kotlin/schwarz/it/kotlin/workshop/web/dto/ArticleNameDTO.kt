package schwarz.it.kotlin.workshop.web.dto

import kotlinx.serialization.Serializable
import schwarz.it.kotlin.workshop.web.entity.Article

@Serializable
data class ArticleNameDTO(
    val identifier: String,
    val name: String,
)

fun Article.toArticleNameDTO() =
    ArticleNameDTO(
        identifier = identifier,
        name = name,
    )
