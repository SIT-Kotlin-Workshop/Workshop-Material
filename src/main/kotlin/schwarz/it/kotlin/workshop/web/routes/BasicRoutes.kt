package schwarz.it.kotlin.workshop.web.routes

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import schwarz.it.kotlin.workshop.web.dto.ShopStatisticsDTO
import java.time.LocalDateTime
import kotlin.random.Random

fun Route.basicRoutes() {
    route("/basic") {
        get("/static.html") {
            call.respondText(contentType = ContentType.Text.Html, status = HttpStatusCode.OK) {
                """
                <html>
                <head>
                    <title>
                        Kodee's online shop
                    </title>
                </head>
                <body>
                    <h1>Welcome at Kodee's online shop</h1>
                </body>
                </htmL>
                """.trimIndent()
            }
        }

        get("/dynamic.html") {
            val totalOrders = Random.nextInt(from = 5, until = 20)
            val inDelivery = Random.nextInt(from = 2, until = totalOrders / 2)

            call.respondText(contentType = ContentType.Text.Html, status = HttpStatusCode.OK) {
                """
                <html>
                <head>
                    <title>
                        Kodee's online shop
                    </title>
                </head>
                <body>
                    <h1>Welcome at Kodee's online shop</h1>
                    Orders at ${LocalDateTime.now()}: $totalOrders total, $inDelivery of which in delivery
                </body>
                </html>
                """.trimIndent()
            }
        }

        get("/decoupled") {
            val totalOrders = Random.nextLong(from = 6, until = 20)
            val inDelivery = Random.nextLong(from = 2, until = totalOrders / 2)

            val response =
                ShopStatisticsDTO(
                    timestamp = LocalDateTime.now().toString(),
                    totalOrders = totalOrders,
                    ordersInDelivery = inDelivery,
                )

            call.respond(HttpStatusCode.OK, response)
        }
    }
}
