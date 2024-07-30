package schwarz.it.kotlin.workshop.web.plugins

import io.github.smiley4.ktorswaggerui.routing.openApiSpec
import io.github.smiley4.ktorswaggerui.routing.swaggerUI
import io.ktor.server.application.Application
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import schwarz.it.kotlin.workshop.web.routes.advancedRoutes
import schwarz.it.kotlin.workshop.web.routes.articlePriceRoutes
import schwarz.it.kotlin.workshop.web.routes.articleRoutes
import schwarz.it.kotlin.workshop.web.routes.basicRoutes
import schwarz.it.kotlin.workshop.web.routes.staticRoutes

fun Application.configureRouting() {
    routing {
        route("swagger") {
            swaggerUI("/api.json")
        }
        route("api.json") {
            openApiSpec()
        }

        staticRoutes()

        basicRoutes()

        advancedRoutes()

        articleRoutes()
        articlePriceRoutes()
    }
}
