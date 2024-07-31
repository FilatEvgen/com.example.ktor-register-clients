package example.com.plugins

import example.com.features.client.configureClientRouting
import example.com.features.master.configureMasterRouting
import example.com.features.record.configureRecordRouting
import example.com.features.service.configureServiceRouting
import io.ktor.server.application.*

fun Application.configureRouting() {
    configureMasterRouting()
    configureClientRouting()
    configureRecordRouting()
    configureServiceRouting()
}
