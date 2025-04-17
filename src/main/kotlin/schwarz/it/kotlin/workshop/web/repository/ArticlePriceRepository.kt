package schwarz.it.kotlin.workshop.web.repository

import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import schwarz.it.kotlin.workshop.web.entity.ArticlePrice
import schwarz.it.kotlin.workshop.web.entity.ArticlePrices
import schwarz.it.kotlin.workshop.web.entity.toArticlePrice
import java.time.Instant

object ArticlePriceRepository {
    fun findPriceActiveForArticleAt(
        articleIdentifier: String,
        date: Instant,
    ) = transaction {
        ArticlePrices
            .selectAll()
            .where(
                (ArticlePrices.articleIdentifier eq articleIdentifier) and
                    (ArticlePrices.startDate lessEq date) and
                    (ArticlePrices.endDate greaterEq date),
            ).singleOrNull()
            ?.toArticlePrice()
    }

    fun findAllPricesFor(articleIdentifier: String): List<ArticlePrice> =
        transaction {
            ArticlePrices
                .selectAll()
                .where(
                    ArticlePrices.articleIdentifier eq articleIdentifier,
                ).orderBy(ArticlePrices.startDate, SortOrder.ASC)
                .map { it.toArticlePrice() }
        }
}
