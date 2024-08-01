package example.com.features.master

import kotlinx.serialization.Serializable

@Serializable
data class Master(
    val name: String,
    val service: String,
    val phone: String,
    val userName: String?,
)
