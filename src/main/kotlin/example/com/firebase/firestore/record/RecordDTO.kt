package example.com.firebase.firestore.record

import kotlinx.serialization.Serializable

@Serializable
data class RecordDTO(
    val id: Int,
    val master: String,
    val client: String,
    val date: String,
    val time: Long,
    val service: String,
)
