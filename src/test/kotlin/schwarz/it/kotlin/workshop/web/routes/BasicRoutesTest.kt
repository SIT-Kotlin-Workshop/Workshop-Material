package schwarz.it.kotlin.workshop.web.routes

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.longs.shouldBeGreaterThanOrEqual
import io.kotest.matchers.longs.shouldBeLessThan
import io.kotest.matchers.shouldBe
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.testApplication
import schwarz.it.kotlin.workshop.web.dto.ShopStatisticsDTO

class BasicRoutesTest : AnnotationSpec() {
    @Test
    fun `endpoint decoupled - returns statistics`() =
        testApplication {
            val client =
                createClient {
                    install(ContentNegotiation) {
                        json()
                    }
                }

            val response = client.get("/basic/decoupled")

            response.status shouldBe HttpStatusCode.OK

            val body = response.body<ShopStatisticsDTO>()

            body.totalOrders shouldBeLessThan 20
            body.ordersInDelivery shouldBeLessThan body.totalOrders
            body.ordersInDelivery shouldBeGreaterThanOrEqual 2
        }
}
