package example.com.Database.Client

import example.com.firestore

object Clients {
    private const val COLLECTION_NAME = "clients"
    private const val NAME = "name"
    private const val PHONE = "phone"
    private const val USER_NAME = "userName"
    private val clientsCollectionRef = firestore.collection(COLLECTION_NAME)


    fun insert(clientDTO: ClientDTO) {
        val clientDocumentRef = clientsCollectionRef.document(clientDTO.phone)
        val clientDocument = clientDocumentRef.get().get()
        if (!clientDocument.exists())
            clientDocumentRef.set(clientDTO)
    }

    fun fetchClient(phoneNumber: String): ClientDTO?{
        val clientDocumentRef = clientsCollectionRef.document(phoneNumber)
        val clientDocument = clientDocumentRef.get().get()
        return if (clientDocument.exists()){
            ClientDTO(
                name = clientDocument.getString(NAME)?: "",
                phone = clientDocument.getString(PHONE)?: "",
                userName = clientDocument.getString(USER_NAME)
            )
        }else{null}
    }
}


