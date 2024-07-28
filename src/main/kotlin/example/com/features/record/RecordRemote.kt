package example.com.features.record

import kotlinx.serialization.Serializable

@Serializable
data class Record(
    val id: String,
    val master: String,
    val client: String,
    val date: String,
    val time: String,
    val service: String,
)