package schwarz.it.kotlin.workshop.web.configuration

import io.ktor.server.config.ApplicationConfig

data class HttpConfig(
    val allowedHost: String?,
    val allowedSchemes: List<String>?,
)

val ApplicationConfig.http: HttpConfig
    get() {
        val host = propertyOrNull("ktor.http.allowedHost")?.getString()
        val schemes =
            propertyOrNull("ktor.http.allowedSchemes")
                ?.getString()
                ?.split(",")
                ?.map { it.trim() }

        return HttpConfig(host, schemes)
    }
