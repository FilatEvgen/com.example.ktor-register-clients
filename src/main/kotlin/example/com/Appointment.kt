package example.com

import kotlinx.serialization.Serializable

@Serializable
data class Appointment(
    val date: String,
    val time: String,
)
