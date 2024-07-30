package schwarz.it.kotlin.workshop.web.routes

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.testing.testApplication
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import schwarz.it.kotlin.workshop.web.configuration.DatabaseFactory
import schwarz.it.kotlin.workshop.web.dto.ArticlePriceDTO
import schwarz.it.kotlin.workshop.web.entity.ArticlePrices
import schwarz.it.kotlin.workshop.web.entity.Articles
import java.math.BigDecimal
import java.time.Instant

class ArticlePriceRoutesTest : AnnotationSpec() {
    @BeforeClass
    fun `before class`() {
        // Make sure that database is started and initialized
        DatabaseFactory.startDatabase(ApplicationConfig(null), true)
    }

    @BeforeEach
    fun `before each`() {
        // Clean up database so that test can start without any entities present
        transaction {
            Articles.deleteAll()
            ArticlePrices.deleteAll()
        }
    }

    @Test
    fun `get article prices - no prices when article unknown`() =
        testApplication {
            val client =
                createClient {
                    install(ContentNegotiation) {
                        json()
                    }
                }

            val articleIdentifier = "123"
            val response = client.get("/article-prices/$articleIdentifier")

            response.status shouldBe HttpStatusCode.OK

            val body = response.body<Collection<ArticlePriceDTO>>()

            body shouldHaveSize 0
        }

    @Test
    fun `get article prices - no prices when article has no prices`() =
        testApplication {
            val client =
                createClient {
                    install(ContentNegotiation) {
                        json()
                    }
                }

            val articleIdentifier = "123"

            transaction {
                Articles.insert {
                    it[this.identifier] = articleIdentifier
                    it[this.name] = "Test"
                    it[this.description] = "Description"
                }
            }

            val response = client.get("/article-prices/$articleIdentifier")

            response.status shouldBe HttpStatusCode.OK

            val body = response.body<Collection<ArticlePriceDTO>>()

            body shouldHaveSize 0
        }

    @Test
    fun `get article prices - shows prices for article`() =
        testApplication {
            val client =
                createClient {
                    install(ContentNegotiation) {
                        json()
                    }
                }

            val articleIdentifier = "123"

            transaction {
                Articles.insert {
                    it[this.identifier] = articleIdentifier
                    it[this.name] = "Test"
                    it[this.description] = "Description"
                }
                Articles.insert {
                    it[this.identifier] = "456"
                    it[this.name] = "Toast"
                    it[this.description] = "Description"
                }

                ArticlePrices.insert {
                    it[this.articleIdentifier] = "123"
                    it[this.price] = BigDecimal(1)
                    it[this.startDate] = Instant.parse("2015-01-01T00:00:00Z")
                    it[this.endDate] = Instant.parse("2019-12-31T23:59:59Z")
                }

                ArticlePrices.insert {
                    it[this.articleIdentifier] = "123"
                    it[this.price] = BigDecimal(2)
                    it[this.startDate] = Instant.parse("2020-01-01T00:00:00Z")
                    it[this.endDate] = Instant.parse("2029-12-31T23:59:59Z")
                }

                ArticlePrices.insert {
                    it[this.articleIdentifier] = "456"
                    it[this.price] = BigDecimal(3)
                    it[this.startDate] = Instant.parse("2020-01-01T00:00:00Z")
                    it[this.endDate] = Instant.parse("2029-12-31T23:59:59Z")
                }
            }

            val response = client.get("/article-prices/$articleIdentifier")

            response.status shouldBe HttpStatusCode.OK

            val body = response.body<Collection<ArticlePriceDTO>>()

            body shouldHaveSize 2
            body shouldContain
                ArticlePriceDTO(
                    price = 1.0,
                    startDate = "2015-01-01T00:00:00Z",
                    endDate = "2019-12-31T23:59:59Z",
                )
            body shouldContain
                ArticlePriceDTO(
                    price = 2.0,
                    startDate = "2020-01-01T00:00:00Z",
                    endDate = "2029-12-31T23:59:59Z",
                )
            body.map { it.price } shouldNotContain 3.0
        }
}
