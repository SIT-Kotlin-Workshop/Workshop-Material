package schwarz.it.kotlin.workshop.web.routes

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isGreaterThanOrEqualTo
import assertk.assertions.isLessThan
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import org.junit.jupiter.api.Test
import schwarz.it.kotlin.workshop.web.dto.ShopStatisticsDTO
import util.integrationTest

class BasicRoutesTest {
    @Test
    fun `endpoint decoupled - returns statistics`() =
        integrationTest { client ->
            val response = client.get("/basic/decoupled")

            assertThat(response.status).isEqualTo(HttpStatusCode.OK)

            val body = response.body<ShopStatisticsDTO>()

            assertThat(body.totalOrders).isLessThan(20)
            assertThat(body.ordersInDelivery).isLessThan(body.totalOrders)
            assertThat(body.ordersInDelivery).isGreaterThanOrEqualTo(2)
        }
}
