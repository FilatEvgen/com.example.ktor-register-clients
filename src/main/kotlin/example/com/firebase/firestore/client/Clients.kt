package example.com.firebase.firestore.client

import example.com.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Clients {
    private const val COLLECTION_NAME = "clients"
    private const val NAME = "name"
    private const val PHONE = "phone"
    private const val USER_NAME = "userName"
    private val clientsCollectionRef = firestore.collection(COLLECTION_NAME)

    suspend fun insert(clientDTO: ClientDTO) = withContext(Dispatchers.IO) {
        val clientDocumentRef = clientsCollectionRef.document(clientDTO.phone)
        val clientDocument = clientDocumentRef.get().get()
        if (!clientDocument.exists())
            clientDocumentRef.set(clientDTO)
    }

    suspend fun fetchClient(phoneNumber: String): ClientDTO? = withContext(Dispatchers.IO) {
        val clientDocumentRef = clientsCollectionRef.document(phoneNumber)
        val clientDocument = clientDocumentRef.get().get()
        return@withContext if (clientDocument.exists()) {
            ClientDTO(
                name = clientDocument.getString(NAME) ?: "",
                phone = clientDocument.getString(PHONE) ?: "",
                userName = clientDocument.getString(USER_NAME)
            )
        } else {
            null
        }
    }
}



