package schwarz.it.kotlin.workshop.web.routes

import io.github.smiley4.ktorswaggerui.dsl.routing.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import schwarz.it.kotlin.workshop.web.dto.toArticlePriceDTO
import schwarz.it.kotlin.workshop.web.repository.ArticlePriceRepository

fun Route.articlePriceRoutes() {
    route("/article-prices") {
        get("/{articleIdentifier}") {
            val articleIdentifier = call.parameters["articleIdentifier"]

            if (articleIdentifier == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing article identifier")
                return@get
            }

            val prices = ArticlePriceRepository.findAllPricesFor(articleIdentifier)

            call.respond(HttpStatusCode.OK, prices.map { it.toArticlePriceDTO() })
        }
    }
}
