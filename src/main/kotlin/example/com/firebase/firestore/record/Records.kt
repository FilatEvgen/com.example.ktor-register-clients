package example.com.firebase.firestore.record

import example.com.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Records {
    private const val COLLECTION_NAME = "record"
    private const val RECORD_ID = "id"
    private const val RECORD_MASTER = "master"
    private const val RECORD_CLIENT = "client"
    private const val RECORD_DATE = "date"
    private const val RECORD_TIME = "time"
    private const val RECORD_SERVICE = "service"
    private val recordCollectionRef = firestore.collection(COLLECTION_NAME)

    suspend fun insert(recordDTO: RecordDTO) = withContext(Dispatchers.IO){
        val recordDocumentRef = recordCollectionRef.document(recordDTO.id.toString())
        val recordDocument = recordDocumentRef.get().get()
        if (!recordDocument.exists())
            recordDocumentRef.set(recordDTO)
    }

    suspend fun fetchRecord(idRecord: Int): RecordDTO? = withContext(Dispatchers.IO){
        val recordDocumentRef = recordCollectionRef.document(idRecord.toString())
        val recordDocument = recordDocumentRef.get().get()
        return@withContext if(recordDocument.exists()){
            RecordDTO(
                id = recordDocument.getString(RECORD_ID)?: "",
                master = recordDocument.getString(RECORD_MASTER)?: "",
                client = recordDocument.getString(RECORD_CLIENT)?: "",
                date = recordDocument.getString(RECORD_DATE)?: "",
                time = recordDocument.getString(RECORD_TIME)?: "",
                service = recordDocument.getString(RECORD_SERVICE)?: "",
            )
        }else{null}

    }
}