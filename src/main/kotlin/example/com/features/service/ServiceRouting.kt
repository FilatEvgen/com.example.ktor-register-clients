package example.com.features.service

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureServiceRouting() {
    routing {
        post("/services") {
            val controller = ServiceController(call)
            controller.createService()
        }
        get("/services") {
            val controller = ServiceController(call)
            controller.getService()
        }
    }
}