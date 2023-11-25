package website.lolegrand

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import website.lolegrand.dao.DatabaseFactory
import website.lolegrand.plugins.configureRouting
import website.lolegrand.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    install(CORS) {
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Delete)
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }
    configureSerialization()
    configureRouting()
}
