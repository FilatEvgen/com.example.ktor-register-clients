package example.com.features.client

import example.com.firebase.firestore.client.ClientDTO
import example.com.firebase.firestore.client.Clients
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlin.random.Random

class ClientController(private val applicationCall: ApplicationCall) {
    suspend fun registerClient() {
        val clientRemote = applicationCall.receive<Client>()
        val clientId = Random.nextInt(1, 10000)
        val clientDTO = ClientDTO(
            id = clientId,
            phone = clientRemote.phone,
            name = clientRemote.name,
            userName = clientRemote.userName
        )
        Clients.insert(clientDTO)
        applicationCall.respond(HttpStatusCode.Created, mapOf("clientId" to clientId))
    }

    suspend fun getClient() {
        val clientId = applicationCall.request.headers["ClientId"]?.toIntOrNull() ?: 0
        if (clientId == 0) {
            applicationCall.respond(HttpStatusCode.BadRequest, "Идентификатор клиента не передан")
            return
        }
        val clientDTO = Clients.fetchClient(clientId)
        if (clientDTO != null) {
            applicationCall.respond(HttpStatusCode.OK, clientDTO)
        } else {
            applicationCall.respond(HttpStatusCode.NotFound, "Клиент не найден")
        }
    }
}