package example.com.features.master

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureMasterRouting() {
    routing {
        post("/masters") {
            val controller = MasterController(call)
            controller.registerMaster()
        }
        get("/masters/get") {
            val controller = MasterController(call)
            controller.getMaster()
        }
    }
}