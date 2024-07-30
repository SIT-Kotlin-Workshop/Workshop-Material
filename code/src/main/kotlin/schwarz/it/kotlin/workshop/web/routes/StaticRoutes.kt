package schwarz.it.kotlin.workshop.web.routes

import io.ktor.server.http.content.staticResources
import io.ktor.server.routing.Route

fun Route.staticRoutes() {
    staticResources("/frontend", "static") {
        default("index.html")
    }
}
