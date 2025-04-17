package schwarz.it.kotlin.workshop.web.routes

import io.github.smiley4.ktoropenapi.get
import io.github.smiley4.ktoropenapi.post
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import schwarz.it.kotlin.workshop.web.dto.AdvancedShopStatisticsDTO
import schwarz.it.kotlin.workshop.web.dto.OrderCreationDTO
import schwarz.it.kotlin.workshop.web.dto.toOrderInfoDTO
import schwarz.it.kotlin.workshop.web.entity.Order
import schwarz.it.kotlin.workshop.web.entity.Status
import schwarz.it.kotlin.workshop.web.util.next
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random

const val ONE_DAY_IN_MINUTES = 24 * 60L

data class OrderRepository(
    val orders: MutableMap<Long, Order> = mutableMapOf(),
)

fun Route.advancedRoutes() {
    val repository = OrderRepository()

    route("/advanced") {
        get("/overview") {
            val response =
                AdvancedShopStatisticsDTO(
                    timestamp = LocalDateTime.now().toString(),
                    totalOrders = repository.orders.size.toLong(),
                    ordersInDelivery = repository.orders.count { it.value.status == Status.IN_DELIVERY }.toLong(),
                    orders = repository.orders.keys.toList(),
                )

            call.respond(HttpStatusCode.OK, response)
        }

        post("/initialize") {
            repository.orders.clear()

            repeat(10) {
                val identifier = Random.nextLong(10_000, 90_000)

                val range = 5 * ONE_DAY_IN_MINUTES
                val minutes = Random.nextLong(0, range) - range / 4
                val now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
                val deliveryDate = now.plusMinutes(minutes)

                repository.orders[identifier] =
                    Order(
                        cost = Random.nextLong(1, 100),
                        estimatedDeliveryDate = deliveryDate,
                        status =
                            if (deliveryDate < now.plusDays(1)) {
                                Status.IN_DELIVERY
                            } else {
                                Status.OPEN
                            },
                    )
            }

            call.respond(HttpStatusCode.OK)
        }

        get("/order/{identifier}") {
            val identifier = call.parameters["identifier"]?.toLongOrNull()

            if (identifier == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val orderInfo = repository.orders[identifier]

            if (orderInfo == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }

            call.respond(HttpStatusCode.OK, orderInfo.toOrderInfoDTO())
        }

        post("/order") {
            val dto = call.receive<OrderCreationDTO>()

            if (dto.cost < 0 || dto.cost > 1_000) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            if (dto.deliveryDay == DayOfWeek.SUNDAY) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val identifier = Random.nextLong(10_000, 90_000)

            repository.orders[identifier] =
                Order(
                    cost = dto.cost,
                    estimatedDeliveryDate =
                        LocalDateTime
                            .now()
                            .truncatedTo(ChronoUnit.DAYS)
                            .plusDays(3)
                            .next(dto.deliveryDay)
                            .plusMinutes(Random.nextLong(0, ONE_DAY_IN_MINUTES)),
                    status = Status.OPEN,
                )

            call.respond(HttpStatusCode.Created, identifier)
        }
    }
}
