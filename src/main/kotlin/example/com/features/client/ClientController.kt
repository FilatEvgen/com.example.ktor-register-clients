package example.com.features.client

import example.com.firebase.firestore.client.ClientDTO
import example.com.firebase.firestore.client.Clients
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*
import kotlin.random.Random

class ClientController(private val applicationCall: ApplicationCall) {
    suspend fun registerClient() {
        val clientRemote = applicationCall.receive<Client>()
        val clientId = clientRemote.id ?: UUID.randomUUID().mostSignificantBits
        val clientDTO = ClientDTO(
            id = clientId.toLong(),
            phone = clientRemote.phone,
            name = clientRemote.name,
            userName = clientRemote.userName
        )
        Clients.insert(clientDTO)
        applicationCall.respond(HttpStatusCode.Created, clientDTO)
    }
    suspend fun getClient() {
        val clientId = applicationCall.request.headers["ClientId"]?.toLongOrNull() ?: 0
        if (clientId.toInt() == 0) {
            applicationCall.respond(HttpStatusCode.BadRequest, "Идентификатор клиента не передан")
        }
        val clientDTO = Clients.fetchClient(clientId)
        if (clientDTO != null) {
            applicationCall.respond(HttpStatusCode.OK, clientDTO)
        } else {
            applicationCall.respond(HttpStatusCode.NotFound, "Клиент не найден")
        }
    }
}