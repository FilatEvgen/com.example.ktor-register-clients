package example.com.features.service

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureServiceRouting() {
    routing {
        post("/services") {
            val controller = ServiceController(call)
            controller.createService()
        }
        post("/services/get") {
            val controller = ServiceController(call)
            controller.getService()
        }
    }
}