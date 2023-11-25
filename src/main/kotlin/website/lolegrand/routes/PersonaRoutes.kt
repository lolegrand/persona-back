package website.lolegrand.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import website.lolegrand.dao.initBddDAO

val dao = initBddDAO()

fun Route.personaRoutes() {
    route("/persona") {
        post {
            val formParameters = call.receiveParameters()
            val firstname = formParameters.getOrFail("firstname")
            val lastname = formParameters.getOrFail("lastname")
            val email = formParameters.getOrFail("email")
            val phone = formParameters.getOrFail("phone")
            val mobile = formParameters.getOrFail("mobile")
            val street = formParameters.getOrFail("street")
            val city = formParameters.getOrFail("city")
            val state = formParameters.getOrFail("state")
            val country = formParameters.getOrFail("country")
            val latitude = formParameters.getOrFail("latitude").toFloat()
            val longitude = formParameters.getOrFail("longitude").toFloat()
            val dateOfBirth = formParameters.getOrFail("dateOfBirth").toLong()
            val thumbnail = formParameters.getOrFail("thumbnail")

            dao.addPersona(
                firstname = firstname,
                lastname = lastname,
                email = email,
                phone = phone,
                mobile = mobile,
                street = street,
                city = city,
                state = state,
                country = country,
                latitude = latitude,
                longitude = longitude,
                dateOfBirth = dateOfBirth,
                thumbnail = thumbnail
            )
            call.respond(dao.allPersona())
        }

        get {
            call.respond(dao.allPersona())
        }

        delete("{id}") {
            val id = call.parameters.getOrFail("id")
            dao.deletePersona(id)
            call.respond(dao.allPersona())
        }
    }
}
