package schwarz.it.kotlin.workshop.web.entity

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Articles : Table("articles") {
    val identifier = varchar("identifier", 255).uniqueIndex()
    val name = varchar("name", 255)
    val description = text("description")

    override val primaryKey = PrimaryKey(identifier, name = "PK_Articles")
}

data class Article(
    val identifier: String,
    val name: String,
    val description: String,
)

fun ResultRow.toArticle() =
    Article(
        identifier = this[Articles.identifier],
        name = this[Articles.name],
        description = this[Articles.description],
    )
