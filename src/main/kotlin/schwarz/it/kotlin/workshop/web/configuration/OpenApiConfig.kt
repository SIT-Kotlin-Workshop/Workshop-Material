package schwarz.it.kotlin.workshop.web.configuration

import io.github.smiley4.ktoropenapi.OpenApi
import io.ktor.server.application.Application
import io.ktor.server.application.install

fun Application.configureOpenApi() {
    install(OpenApi) {
        info {
            title = "Kodee shop API"
            version = "0.0.1"
            description = "APIs for Kodee's little online shop"
        }
        server {
            url = "http://localhost:8080"
            description = "Development"
        }
    }
}
