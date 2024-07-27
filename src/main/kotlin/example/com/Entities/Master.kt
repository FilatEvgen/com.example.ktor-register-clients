package example.com.Entities

data class Master(
    val id: String,
    val name: String,
    val service: Service,
    val phone: String,
    val userName: String?,
)

data class Client(
    val id: String,
    val name: String,
    val phone: String,
    val userName: String?,
)

data class Record(
    val id: String,
    val master: Master,
    val client: Client,
    val date: String,
    val time: String,
    val service: Service,
)

data class Service(
    val id: String,
    val name: String,
)