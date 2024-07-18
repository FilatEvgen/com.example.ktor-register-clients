package example.com

import example.com.Database.populateTablesUntilEndOfYear
import example.com.plugins.configureRouting
import example.com.plugins.configureSerialization
import example.com.plugins.configureSockets
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.Database
import java.time.LocalDateTime

fun main() {
    val dbUrl = System.getenv("DB_URL")
    val dbDriver = System.getenv("DB_DRIVER")
    val dbUser = System.getenv("DB_USER")
    val dbPassword = System.getenv("DB_PASSWORD")
    println("DB_URL: $dbUrl")
    println("DB_DRIVER: $dbDriver")
    println("DB_USER: $dbUser")
    println("DB_PASSWORD: $dbPassword")
    try {
        Database.connect(url = dbUrl, driver = dbDriver, user = dbUser, password = dbPassword)
        println("Соединение с базой данных успешно установлено")
    }catch (e: Exception){
        println("Error connecting to database: $e")
    }
    populateTablesUntilEndOfYear()
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        while (true) {
            val now = LocalDateTime.now()
            if (now.monthValue == 1 && now.dayOfMonth == 1) {
                populateTablesUntilEndOfYear()
            }
            delay(24 * 60 * 60 * 1000L)
        }
    }
}
//1
fun Application.module() {
    configureSockets()
    configureSerialization()
    configureRouting()
}
