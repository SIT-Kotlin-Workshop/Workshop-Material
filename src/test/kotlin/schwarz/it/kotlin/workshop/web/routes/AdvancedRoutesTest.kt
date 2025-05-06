package schwarz.it.kotlin.workshop.web.routes

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isZero
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import org.junit.jupiter.api.Test
import schwarz.it.kotlin.workshop.web.dto.AdvancedShopStatisticsDTO
import schwarz.it.kotlin.workshop.web.dto.OrderCreationDTO
import schwarz.it.kotlin.workshop.web.dto.OrderInfoDTO
import schwarz.it.kotlin.workshop.web.entity.Status
import util.integrationTest
import java.time.DayOfWeek
import java.time.LocalDateTime

class AdvancedRoutesTest {
    @Test
    fun `overview - with zero orders`() =
        integrationTest { client ->

            val response = client.get("/advanced/overview")

            assertThat(response.status).isEqualTo(HttpStatusCode.OK)

            val body = response.body<AdvancedShopStatisticsDTO>()

            assertThat(body.totalOrders).isZero()
            assertThat(body.ordersInDelivery).isZero()
            assertThat(body.orders).isEmpty()
        }

    @Test
    fun `create and show an order`() =
        integrationTest { client ->

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

            assertThat(createResponse.status).isEqualTo(HttpStatusCode.Created)

            val overviewResponse = client.get("/advanced/overview")

            assertThat(overviewResponse.status).isEqualTo(HttpStatusCode.OK)

            val statistics = overviewResponse.body<AdvancedShopStatisticsDTO>()

            assertThat(statistics.totalOrders).isEqualTo(1)
            assertThat(statistics.ordersInDelivery).isZero()
            assertThat(statistics.orders).hasSize(1)

            val identifier = statistics.orders.first()

            val detailsResponse = client.get("/advanced/order/$identifier")

            assertThat(detailsResponse.status).isEqualTo(HttpStatusCode.OK)

            val orderInfo = detailsResponse.body<OrderInfoDTO>()

            assertThat(orderInfo.status).isEqualTo(Status.OPEN.name)
            assertThat(orderInfo.cost).isEqualTo(100)
            assertThat(LocalDateTime.parse(orderInfo.estimatedDeliveryDate).dayOfWeek).isEqualTo(DayOfWeek.MONDAY)
        }
}
