package example.com.features.client

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureClientRouting() {
    routing {
        post("/clients") {
            val controller = ClientController(call)
            controller.registerClient()
        }
        post("/clients/get") {
            val controller = ClientController(call)
            controller.getClient()
        }
    }
}
