package util

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.testing.ApplicationTestBuilder
import io.ktor.server.testing.testApplication

fun integrationTest(block: suspend ApplicationTestBuilder.(HttpClient) -> Unit) =
    testApplication {
        environment { config = ApplicationConfig("application.conf") }

        val client =
            createClient {
                install(ContentNegotiation) {
                    json()
                }
            }

        block(client)
    }
