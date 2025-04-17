package schwarz.it.kotlin.workshop.web.plugins

import io.github.smiley4.ktoropenapi.openApi
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
        route("openapi_v3.1.json") {
            openApi()
        }

        staticRoutes()

        basicRoutes()

        advancedRoutes()

        articleRoutes()
        articlePriceRoutes()
    }
}
