package example.com.features.record

import example.com.firebase.firestore.record.RecordDTO
import example.com.firebase.firestore.record.Records
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class RecordController(private val applicationCall: ApplicationCall) {
    suspend fun createRecord() {
        val recordRemote = applicationCall.receive<Record>()
        val recordDTO = RecordDTO(
            id = recordRemote.id,
            master = recordRemote.master,
            client = recordRemote.client,
            date = recordRemote.date,
            time = recordRemote.time,
            service = recordRemote.service
        )
        Records.insert(recordDTO)
        applicationCall.respond(HttpStatusCode.Created, "Запись успешно создана")
    }

    suspend fun getRecord() {
        val id = applicationCall.request.headers["Id"] ?: ""
        if (id.isNullOrEmpty()) {
            applicationCall.respond(HttpStatusCode.BadRequest, "ID записи не передан")
            return
        }
        val recordDTO = Records.fetchRecord(id.toInt())
        if (recordDTO != null) {
            applicationCall.respond(HttpStatusCode.OK, recordDTO)
        } else {
            applicationCall.respond(HttpStatusCode.NotFound, "Запись не найдена")
        }
    }
}