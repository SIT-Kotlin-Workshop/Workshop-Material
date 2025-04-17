package schwarz.it.kotlin.workshop.web.configuration

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.ktor.server.config.ApplicationConfig
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import schwarz.it.kotlin.workshop.web.entity.Articles
import schwarz.it.kotlin.workshop.web.entity.toArticle
import java.sql.Connection

class DatabaseIntegrationTest : AnnotationSpec() {
    private lateinit var connection: Connection

    @BeforeClass
    fun `before class`() {
        // Make sure that database is started and initialized
        DatabaseFactory.startDatabase(ApplicationConfig(null), true)
    }

    @Test
    fun `insert and retrieve`() {
        transaction {
            Articles.insert {
                it[this.identifier] = "123"
                it[this.name] = "Test"
                it[this.description] = "Description"
            }
        }

        val articles =
            transaction {
                Articles
                    .selectAll()
                    .map { it.toArticle() }
            }

        articles.size shouldBe 1
        articles.single().identifier shouldBe "123"
        articles.single().name shouldBe "Test"
        articles.single().description shouldBe "Description"
    }
}
