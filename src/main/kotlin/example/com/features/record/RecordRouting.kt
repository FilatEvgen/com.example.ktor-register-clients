package example.com.features.record

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRecordRouting() {
    routing {
        post("/records") {
            val controller = RecordController(call)
            controller.createRecord()
        }
        post("/records/get") {
            val controller = RecordController(call)
            controller.getRecord()
        }
    }
}