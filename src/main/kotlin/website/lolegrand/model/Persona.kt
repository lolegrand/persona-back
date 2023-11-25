package website.lolegrand.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.time.LocalDate
import java.util.UUID

@Serializable
data class Persona(
    val id: String = UUID.randomUUID().toString(),
    val firstname: String,
    val lastname: String,
    val email: String,
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val latitude: Float,
    val longitude: Float,
    val dateOfBirth: Long,
    val phone: String,
    val mobile: String,
    val thumbnail: String
)

object Personas : Table() {
    val id = varchar("id", 128)
    val firstname = varchar("firstname", 128)
    val lastname = varchar("lastname", 128)
    val email = varchar("email", 128)
    val street = varchar("street", 128)
    val city = varchar("city", 128)
    val state = varchar("state", 128)
    val country = varchar("country", 128)
    val latitude = float("latitude")
    val longitude = float("longitude")
    val dateOfBirth = long("dateOfBirth")
    val phone = varchar("phone", 32)
    val mobile = varchar("mobile", 32)
    val thumbnail = varchar("thumbnail", 32768)

    override val primaryKey = PrimaryKey(id)
}
