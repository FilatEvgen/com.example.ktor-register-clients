package example.com.features.master

import example.com.firebase.firestore.master.MasterDTO
import example.com.firebase.firestore.master.Masters
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class MasterController(private val applicationCall: ApplicationCall) {
    suspend fun registerMaster() {
        val masterRemote = applicationCall.receive<Master>()
        val existingMaster = Masters.fetchMaster(masterRemote.phone)
        if (existingMaster != null) {
            applicationCall.respond(HttpStatusCode.Conflict, "Мастер уже зарегестрирован")
        } else {
            val masterDTO = MasterDTO(
                name = masterRemote.name,
                service = masterRemote.service,
                phone = masterRemote.phone,
                userName = masterRemote.userName
            )
            Masters.insert(masterDTO)
            applicationCall.respond(HttpStatusCode.Created, masterDTO)
        }
    }

    suspend fun getMaster() {
        val master = applicationCall.receive<Master>()
        if (master.phone.isEmpty()) {
            applicationCall.respond(HttpStatusCode.BadRequest, "Номер телефона не передан")
            return
        }
        val masterDTO = Masters.fetchMaster(master.phone)
        if (masterDTO != null) {
            applicationCall.respond(HttpStatusCode.OK, masterDTO)
        } else {
            applicationCall.respond(HttpStatusCode.NotFound, "Мастер не найден")
        }
    }
}