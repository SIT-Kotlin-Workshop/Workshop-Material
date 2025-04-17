package schwarz.it.kotlin.workshop.web.routes

import io.github.smiley4.ktoropenapi.get
import io.github.smiley4.ktoropenapi.post
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import schwarz.it.kotlin.workshop.web.dto.ArticleCreationDTO
import schwarz.it.kotlin.workshop.web.dto.ArticleDetailsDTO
import schwarz.it.kotlin.workshop.web.dto.toArticleNameDTO
import schwarz.it.kotlin.workshop.web.repository.ArticlePriceRepository
import schwarz.it.kotlin.workshop.web.repository.ArticleRepository
import schwarz.it.kotlin.workshop.web.service.ArticleService
import java.time.Instant

fun Route.articleRoutes() {
    route("/articles") {
        get("/list/") {
            val articles = ArticleService.findAllBySearchString()
            call.respond(HttpStatusCode.OK, articles.map { it.toArticleNameDTO() })
        }
        get("/list/{searchString}") {
            val searchString = call.parameters["searchString"]

            val articles = ArticleService.findAllBySearchString(searchString)

            call.respond(HttpStatusCode.OK, articles.map { it.toArticleNameDTO() })
        }

        post {
            val dto = call.receive<ArticleCreationDTO>()

            if (dto.name.isBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Illegal empty name")
                return@post
            }

            val article = ArticleService.createArticle(dto)

            call.respond(HttpStatusCode.Created, article.identifier)
        }

        get("/{identifier}") {
            val identifier = call.parameters["identifier"]

            if (identifier.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Missing article identifier")
                return@get
            }

            val article = ArticleRepository.findArticleByIdentifier(identifier)

            if (article == null) {
                call.respond(HttpStatusCode.NotFound, "Article not found")
                return@get
            }

            val price =
                ArticlePriceRepository.findPriceActiveForArticleAt(
                    articleIdentifier = identifier,
                    date = Instant.now(),
                )

            val articleDetails =
                ArticleDetailsDTO(
                    description = article.description,
                    currentPrice = price?.price,
                    validFrom = price?.startDate.toString(),
                    validTo = price?.endDate.toString(),
                )

            call.respond(HttpStatusCode.OK, articleDetails)
        }
    }
}
