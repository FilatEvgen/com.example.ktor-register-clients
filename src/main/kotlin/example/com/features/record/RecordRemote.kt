package example.com.features.record

import kotlinx.serialization.Serializable

@Serializable
data class Record(
    val id: Int,
    val master: String,
    val client: String,
    val date: String,
    val time: Long,
    val service: String,
)