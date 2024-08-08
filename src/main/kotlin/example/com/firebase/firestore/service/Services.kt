package example.com.firebase.firestore.service

import example.com.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Services {
    private const val COLLECTION_NAME = "service"
    private const val SERVICE_ID = "id"
    private const val SERVICE_NAME = "name"
    private val serviceCollectionRef = firestore.collection(COLLECTION_NAME)

    suspend fun insert(serviceDTO: ServiceDTO) = withContext(Dispatchers.IO){
        val serviceDocumentRef = serviceCollectionRef.document(serviceDTO.id.toString())
        val serviceDocument = serviceDocumentRef.get().get()
        if (!serviceDocument.exists())
            serviceDocumentRef.set(serviceDTO)
    }
    suspend fun fetchService(idService: Int): ServiceDTO? = withContext(Dispatchers.IO){
        val serviceDocumentRef = serviceCollectionRef.document(idService.toString())
        val serviceDocument =serviceDocumentRef.get().get()
        return@withContext if (serviceDocument.exists()){
            ServiceDTO(
                id = serviceDocument.getLong(SERVICE_ID)?: 0L,
                name = serviceDocument.getString(SERVICE_NAME)?: "",
            )
        }else{null}
    }
}