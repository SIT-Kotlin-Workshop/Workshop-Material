package schwarz.it.kotlin.workshop.web.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import schwarz.it.kotlin.workshop.web.routes.advancedRoutes
import schwarz.it.kotlin.workshop.web.routes.basicRoutes
import schwarz.it.kotlin.workshop.web.routes.exerciseRoutes
import schwarz.it.kotlin.workshop.web.routes.staticRoutes

fun Application.configureRouting() {
    routing {
        staticRoutes()

        basicRoutes()

        exerciseRoutes()

        advancedRoutes()
    }
}
