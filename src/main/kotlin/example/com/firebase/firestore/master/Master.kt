package example.com.firebase.firestore.master

import example.com.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Master {
    private const val COLLECTION_MASTER_NAME = "master"
    private const val NAME = "name"
    private const val SERVICE = "service"
    private const val PHONE = "phone"
    private const val USER_NAME = "userName"
    private val masterCollectionRef = firestore.collection(COLLECTION_MASTER_NAME)

    suspend fun insert(masterDTO: MasterDTO) = withContext(Dispatchers.IO) {
        val masterDocumentRef = masterCollectionRef.document(masterDTO.phone)
        val masterDocument = masterDocumentRef.get().get()
        if (!masterDocument.exists())
            masterDocumentRef.set(masterDTO)
    }

    suspend fun fetchMaster(phoneNumber: String): MasterDTO? = withContext(Dispatchers.IO) {
        val masterDocumentRef = masterCollectionRef.document(phoneNumber)
        val masterDocument = masterDocumentRef.get().get()
        return@withContext if (masterDocument.exists()) {
            MasterDTO(
                name = masterDocument.getString(NAME) ?: "",
                service = masterDocument.getString(SERVICE) ?: "",
                phone = masterDocument.getString(PHONE) ?: "",
                userName = masterDocument.getString(USER_NAME) ?: "",
            )
        } else {
            null
        }
    }
}