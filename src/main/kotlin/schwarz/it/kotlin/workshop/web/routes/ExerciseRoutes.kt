package schwarz.it.kotlin.workshop.web.routes

import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

/**
 * The frontend for this exercise is available at
 * `http://localhost:8080/frontend/exercise.html`.
 */
fun Route.exerciseRoutes() {
    var text = "This is a text stored on the server."

    route("/exercise") {
        get("/text") {
            call.respondText(text)
        }
        post("/text") {
            // Update the text to the request body to make the "Update" button work.
            // Hint: Access the `call` object
        }
        // Add another endpoint here that handles HTTP POST requests for "/exercise/text/reset"
        // and resets the text to its default value

        // Add another endpoint here that handles HTTP GET requests for "/exercise/text/stats"
        // that responds with a message how often has the text been updated
        // e.g. "Updated 8 times, last on 2025-05-06T13:45:00"
    }
}
