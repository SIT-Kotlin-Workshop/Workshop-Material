package schwarz.it.kotlin.workshop.web.repository

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import schwarz.it.kotlin.workshop.web.entity.Article
import schwarz.it.kotlin.workshop.web.entity.Articles
import schwarz.it.kotlin.workshop.web.entity.toArticle

object ArticleRepository {
    fun findArticleByIdentifier(identifier: String) =
        transaction {
            Articles
                .selectAll()
                .where { Articles.identifier eq identifier }
                .map { it.toArticle() }
                .singleOrNull()
        }

    fun findAll() =
        transaction {
            Articles.selectAll().map { it.toArticle() }
        }

    fun findAllBySearchString(searchString: String) =
        transaction {
            Articles
                .selectAll()
                .where { Articles.identifier eq searchString }
                .map { it.toArticle() }
        }

    fun save(article: Article) {
        transaction {
            Articles.insert {
                it[identifier] = article.identifier
                it[name] = article.name
                it[description] = article.description
            }
        }
    }
}
