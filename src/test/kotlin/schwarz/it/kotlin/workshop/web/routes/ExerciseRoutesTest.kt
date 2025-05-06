package schwarz.it.kotlin.workshop.web.routes

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import org.junit.jupiter.api.Test
import util.integrationTest

class ExerciseRoutesTest {
    @Test
    fun `get - answers with some text`() =
        integrationTest { client ->
            val response = client.get("/exercise/text")

            assertThat(response.status).isEqualTo(HttpStatusCode.OK)

            assertThat(response.bodyAsText()).isNotNull()
        }

    // Write a test for checking that after POSTing an update and then GETing the text, it has been updated

    // TODO: Remove solution

    @Test
    fun `post then get - answers with updated text`() =
        integrationTest { client ->
            val responsePOST =
                client.post("/exercise/text") {
                    setBody("Test text")
                }

            assertThat(responsePOST.status).isEqualTo(HttpStatusCode.OK)

            val responseGET =
                client.get("/exercise/text")

            assertThat(responseGET.status).isEqualTo(HttpStatusCode.OK)

            assertThat(responseGET.bodyAsText()).isEqualTo("Test text")
        }
}
