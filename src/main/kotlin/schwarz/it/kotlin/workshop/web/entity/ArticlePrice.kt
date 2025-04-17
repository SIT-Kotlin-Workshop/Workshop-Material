package schwarz.it.kotlin.workshop.web.entity

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

object ArticlePrices : Table("article_prices") {
    val articleIdentifier = varchar("article_identifier", 255)
    val price = decimal("price", 6, 2)
    val startDate = timestamp("start_date")
    val endDate = timestamp("end_date")
}

data class ArticlePrice(
    val articleIdentifier: String,
    val price: Double,
    val startDate: Instant,
    val endDate: Instant,
)

fun ResultRow.toArticlePrice() =
    ArticlePrice(
        articleIdentifier = this[ArticlePrices.articleIdentifier],
        price = this[ArticlePrices.price].toDouble(),
        startDate = this[ArticlePrices.startDate],
        endDate = this[ArticlePrices.endDate],
    )
