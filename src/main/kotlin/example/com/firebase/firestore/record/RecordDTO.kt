package example.com.firebase.firestore.record

import kotlinx.serialization.Serializable

@Serializable
data class RecordDTO(
    val id: Long,
    val master: String,
    val client: String,
    val date: String,
    val time: String,
    val service: String,
)
