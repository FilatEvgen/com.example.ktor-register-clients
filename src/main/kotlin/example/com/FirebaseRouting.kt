package example.com

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureFirebaseTestRoute(){
    routing {
        get("/user") {
            try {
                firestore.collection("user").add(User(id = 1, name = "Filat"))
                call.respond(HttpStatusCode.OK)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Ошибка добавления пользователя")
            }
        }
    }
}
