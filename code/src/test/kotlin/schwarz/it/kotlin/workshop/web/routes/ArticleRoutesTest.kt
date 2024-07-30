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
import schwarz.it.kotlin.workshop.web.dto.ArticleNameDTO
import schwarz.it.kotlin.workshop.web.entity.Articles

class ArticleRoutesTest : AnnotationSpec() {
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
        }
    }

    @Test
    fun `list articles - 0 articles`() =
        testApplication {
            val client =
                createClient {
                    install(ContentNegotiation) {
                        json()
                    }
                }

            val response = client.get("/articles/list/")

            response.status shouldBe HttpStatusCode.OK

            val body = response.body<Collection<ArticleNameDTO>>()

            body shouldHaveSize 0
        }

    @Test
    fun `list articles - 1 article`() =
        testApplication {
            val client =
                createClient {
                    install(ContentNegotiation) {
                        json()
                    }
                }

            transaction {
                Articles.insert {
                    it[this.identifier] = "123"
                    it[this.name] = "Test"
                    it[this.description] = "Description"
                }
            }

            val response = client.get("/articles/list/")

            response.status shouldBe HttpStatusCode.OK

            val body = response.body<Collection<ArticleNameDTO>>()

            body shouldHaveSize 1
            body.single() shouldBe ArticleNameDTO("123", "Test")
        }

    @Test
    fun `list articles with search - 2 articles, one of which matches`() =
        testApplication {
            val client =
                createClient {
                    install(ContentNegotiation) {
                        json()
                    }
                }

            val searchString = "123"

            transaction {
                Articles.insert {
                    it[this.identifier] = searchString
                    it[this.name] = "Test"
                    it[this.description] = "Description"
                }
                Articles.insert {
                    it[this.identifier] = "456"
                    it[this.name] = "Toast"
                    it[this.description] = "Description"
                }
            }

            val response = client.get("/articles/list/$searchString")

            response.status shouldBe HttpStatusCode.OK

            val body = response.body<Collection<ArticleNameDTO>>()

            body shouldHaveSize 1
            body shouldContain ArticleNameDTO("123", "Test")
            body shouldNotContain ArticleNameDTO("456", "Toast")
        }
}
