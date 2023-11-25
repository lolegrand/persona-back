package website.lolegrand.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import website.lolegrand.routes.personaRoutes


fun Application.configureRouting() {
    routing {
        personaRoutes()
    }
}
