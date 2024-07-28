package example.com.features.client

import kotlinx.serialization.Serializable

@Serializable
data class Client(
    val id: String,
    val name: String,
    val phone: String,
    val userName: String?,
)