package schwarz.it.kotlin.workshop.web

import io.ktor.events.Events
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStopped
import io.ktor.server.cio.EngineMain
import org.h2.tools.Server
import schwarz.it.kotlin.workshop.web.configuration.DatabaseFactory
import schwarz.it.kotlin.workshop.web.configuration.configureHTTP
import schwarz.it.kotlin.workshop.web.configuration.configureSwagger
import schwarz.it.kotlin.workshop.web.plugins.configureRouting

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureHTTP()

    configureRouting()
    configureSwagger()

    DatabaseFactory.startDatabase(environment.config, isTest())

    if (!isTest()) {
        startH2Console(environment.monitor)
    }
}

private fun Application.isTest() = environment.config.propertyOrNull("custom.test")?.getString() == true.toString()

private fun startH2Console(monitor: Events) {
    val h2Server = Server.createWebServer("-webPort", "8082", "-webAllowOthers", "-web", "-browser").start()
    monitor.subscribe(ApplicationStopped) {
        h2Server.stop()
    }
}
