package schwarz.it.kotlin.workshop.web

import io.ktor.server.application.Application
import io.ktor.server.cio.EngineMain
import schwarz.it.kotlin.workshop.web.configuration.configureHTTP
import schwarz.it.kotlin.workshop.web.plugins.configureRouting

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureHTTP()
    configureRouting()
}
