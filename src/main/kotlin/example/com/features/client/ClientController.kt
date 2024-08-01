package example.com.features.client

import example.com.firebase.firestore.client.ClientDTO
import example.com.firebase.firestore.client.Clients
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ClientController(private val applicationCall: ApplicationCall) {
    suspend fun registerClient() {
        val clientRemote = applicationCall.receive<Client>()
        val existingClient = Clients.fetchClient(clientRemote.phone)
        if (existingClient != null) {
            applicationCall.respond(HttpStatusCode.Conflict, "Пользователь уже зарегестрирован")
        } else {
            val clientDTO = ClientDTO(
                name = clientRemote.name,
                phone = clientRemote.phone,
                userName = clientRemote.userName
            )
            Clients.insert(clientDTO)
            applicationCall.respond(HttpStatusCode.Created, clientDTO)
        }
    }

    suspend fun getClient() {
        val phone = applicationCall.parameters["phone"]
        if (phone.isNullOrEmpty()) {
            applicationCall.respond(HttpStatusCode.BadRequest, "Номер телефона не передан")
            return
        }
        val clientDTO = Clients.fetchClient(phone)
        if (clientDTO != null) {
            applicationCall.respond(HttpStatusCode.OK, clientDTO)
        } else {
            applicationCall.respond(HttpStatusCode.NotFound, "Клиент не найден")
        }
    }
}
