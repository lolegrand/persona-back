package website.lolegrand.dao

import website.lolegrand.model.Persona

interface DAOPersona {
    suspend fun allPersona(): List<Persona>

    suspend fun deletePersona(id: String): Boolean

    suspend fun addPersona(
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
    ): Persona?
}