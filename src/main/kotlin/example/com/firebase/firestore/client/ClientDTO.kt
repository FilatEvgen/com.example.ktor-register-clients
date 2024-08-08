package example.com.firebase.firestore.client

import kotlinx.serialization.Serializable

@Serializable
data class ClientDTO (
    val id: Long,
    val name: String,
    val phone: String,
    val userName: String?,
)
