package example.com.features.service

import kotlinx.serialization.Serializable

@Serializable

data class Service(
    val id: Int,
    val name: String,
)