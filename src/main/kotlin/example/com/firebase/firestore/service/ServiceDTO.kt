package example.com.firebase.firestore.service

import kotlinx.serialization.Serializable

@Serializable
data class ServiceDTO(
    val id: Long,
    val name: String,
)
