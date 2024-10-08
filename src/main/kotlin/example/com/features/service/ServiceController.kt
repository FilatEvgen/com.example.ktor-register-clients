    package example.com.features.service

    import example.com.firebase.firestore.service.ServiceDTO
    import example.com.firebase.firestore.service.Services
    import io.ktor.http.*
    import io.ktor.server.application.*
    import io.ktor.server.request.*
    import io.ktor.server.response.*

    class ServiceController(private val applicationCall: ApplicationCall) {
        suspend fun createService() {
            val serviceRemote = applicationCall.receive<Service>()
            val serviceDTO = ServiceDTO(
                id = serviceRemote.id,
                name = serviceRemote.name
            )
            Services.insert(serviceDTO)
            applicationCall.respond(HttpStatusCode.Created, "Услуга успешно создана")
        }

        suspend fun getService() {
            val id = applicationCall.request.headers["id"]?: ""
            if (id.isNullOrEmpty()) {
                applicationCall.respond(HttpStatusCode.BadRequest, "ID услуги не передан")
                return
            }
            val serviceDTO = Services.fetchService(id.toInt())
            if (serviceDTO != null) {
                applicationCall.respond(HttpStatusCode.OK, serviceDTO)
            } else {
                applicationCall.respond(HttpStatusCode.NotFound, "Услуга не найдена")
            }
        }
    }