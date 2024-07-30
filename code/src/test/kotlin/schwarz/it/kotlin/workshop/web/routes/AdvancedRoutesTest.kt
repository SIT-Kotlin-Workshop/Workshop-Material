package schwarz.it.kotlin.workshop.web.routes

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.testApplication
import schwarz.it.kotlin.workshop.web.dto.AdvancedShopStatisticsDTO
import schwarz.it.kotlin.workshop.web.dto.OrderCreationDTO
import schwarz.it.kotlin.workshop.web.dto.OrderInfoDTO
import schwarz.it.kotlin.workshop.web.entity.Status
import java.time.DayOfWeek
import java.time.LocalDateTime

class AdvancedRoutesTest : AnnotationSpec() {
    @Test
    fun `overview - with zero orders`() =
        testApplication {
            val client =
                createClient {
                    install(ContentNegotiation) {
                        json()
                    }
                }

            val response = client.get("/advanced/overview")

            response.status shouldBe HttpStatusCode.OK

            val body = response.body<AdvancedShopStatisticsDTO>()

            body.totalOrders shouldBe 0
            body.ordersInDelivery shouldBe 0
            body.orders shouldHaveSize 0
        }

    @Test
    fun `create and show an order`() =
        testApplication {
            val client =
                createClient {
                    install(ContentNegotiation) {
                        json()
                    }
                }

            val createResponse =
                client.post("/advanced/order") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        OrderCreationDTO(
                            cost = 100,
                            deliveryDay = DayOfWeek.MONDAY,
                        ),
                    )
                }

            createResponse.status shouldBe HttpStatusCode.Created

            val overviewResponse = client.get("/advanced/overview")

            overviewResponse.status shouldBe HttpStatusCode.OK

            val statistics = overviewResponse.body<AdvancedShopStatisticsDTO>()

            statistics.totalOrders shouldBe 1
            statistics.ordersInDelivery shouldBe 0
            statistics.orders shouldHaveSize 1

            val identifier = statistics.orders.first()

            val detailsResponse = client.get("/advanced/order/$identifier")

            detailsResponse.status shouldBe HttpStatusCode.OK

            val orderInfo = detailsResponse.body<OrderInfoDTO>()

            orderInfo.status shouldBe Status.OPEN.name
            orderInfo.cost shouldBe 100
            LocalDateTime.parse(orderInfo.estimatedDeliveryDate).dayOfWeek shouldBe DayOfWeek.MONDAY
        }
}
