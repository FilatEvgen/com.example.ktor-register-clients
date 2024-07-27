package example.com

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureFirebaseTestRoute() {
    routing {
        get("/user") {
            try {
                firestore.collection("user").add(Appointment(date = "15", time = "09:00"))
                call.respond(HttpStatusCode.OK)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Ошибка добавления пользователя")
            }
        }

        delete("/user/{id}") {
            val id = call.parameters["id"]
            if (id != null) {
                try {
                    firestore.collection("user").document(id).delete()
                    call.respond(HttpStatusCode.OK)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, "Ошибка удаления пользователя")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Требуется идентификатор")
            }
        }

        patch("/user/{id}") {
            val id = call.parameters["id"]
            val user = call.receive<Appointment>()
            if (id != null) {
                try {
                    val document = firestore.collection("user").document(id)
                    val userData = mapOf(
                        "date" to user.date,
                        "time" to user.time
                    )
                    document.update(userData)
                    call.respond(HttpStatusCode.OK)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, "Ошибка обновления пользователя")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Требуется идентификатор")
            }
        }
    }
}