package example.com

import com.google.cloud.firestore.Firestore
import com.google.firebase.cloud.FirestoreClient
import example.com.firebase.FirebaseAdmin
import example.com.plugins.configureRouting
import example.com.plugins.configureSerialization
import example.com.plugins.configureSockets
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

lateinit var firestore: Firestore

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    FirebaseAdmin.init()
    firestore = FirestoreClient.getFirestore()
    configureSockets()
    configureSerialization()
    configureRouting()
}
