package example.com.firebase.firestore.master

import kotlinx.serialization.Serializable

@Serializable
data class MasterDTO(
    val name: String,
    val service: String,
    val phone: String,
    val userName: String?,
)