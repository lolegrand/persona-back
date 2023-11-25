package website.lolegrand.dao

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import website.lolegrand.model.Persona
import website.lolegrand.model.Personas

class DAOPersonaBdd : DAOPersona {

    private fun resultRowToPersona(row: ResultRow) = Persona(
        id = row[Personas.id],
        firstname = row[Personas.firstname],
        lastname = row[Personas.lastname],
        email = row[Personas.email],
        phone = row[Personas.phone],
        mobile = row[Personas.mobile],
        street = row[Personas.street],
        city = row[Personas.city],
        state = row[Personas.state],
        country = row[Personas.country],
        latitude = row[Personas.latitude],
        longitude = row[Personas.longitude],
        dateOfBirth = row[Personas.dateOfBirth],
        thumbnail = row[Personas.thumbnail]
    )

    override suspend fun allPersona() : List<Persona> = dbQuery {
        Personas.selectAll().map(::resultRowToPersona)
    }

    override suspend fun addPersona(
        firstname: String,
        lastname: String,
        email: String,
        street: String,
        city: String,
        state: String,
        country: String,
        latitude: Float,
        longitude: Float,
        dateOfBirth: Long,
        phone: String,
        mobile: String,
        thumbnail: String
    ): Persona? = dbQuery {
        val persona = Persona(
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

        val insertStatement = Personas.insert {
            it[Personas.id] = persona.id
            it[Personas.firstname] = persona.firstname
            it[Personas.lastname] = persona.lastname
            it[Personas.email] = persona.email
            it[Personas.phone] = persona.phone
            it[Personas.mobile] = persona.mobile
            it[Personas.street] = persona.street
            it[Personas.city] = persona.city
            it[Personas.state] = persona.state
            it[Personas.country] = persona.country
            it[Personas.latitude] = persona.latitude
            it[Personas.longitude] = persona.longitude
            it[Personas.dateOfBirth] = persona.dateOfBirth
            it[Personas.thumbnail] = persona.thumbnail
        }

        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToPersona)
    }

    override suspend fun deletePersona(id: String): Boolean = dbQuery {
        Personas.deleteWhere { Personas.id eq id } > 0
    }

}

fun initBddDAO() = DAOPersonaBdd().apply {
    runBlocking {
        if (allPersona().isEmpty()) {
            addPersona(
                firstname = "Friedrich-Wilhelm",
                lastname = "Ruhland",
                email = "friedrich-wilhelm.ruhland@example.com",
                street = "8294 Bachstraße",
                city = "Ingolstadt",
                state = "Saarland",
                country = "Germany",
                latitude = -54.4179f,
                longitude = -70.9127f,
                dateOfBirth = 1113290618,
                phone = "0637-8402279",
                mobile = "0179-8758349",
                thumbnail = "https://randomuser.me/api/portraits/men/75.jpg"
            )
        }
    }
}
