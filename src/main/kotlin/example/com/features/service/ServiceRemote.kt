package example.com.features.service

import kotlinx.serialization.Serializable

@Serializable

data class Service(
    val id: String,
    val name: String,
)