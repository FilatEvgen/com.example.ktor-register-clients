package example.com.firebase.firestore.client

import com.google.cloud.firestore.annotation.DocumentId
import kotlinx.serialization.Serializable

@Serializable
data class ClientDTO (
    val id: Int,
    val name: String,
    val phone: String,
    val userName: String?,
)
