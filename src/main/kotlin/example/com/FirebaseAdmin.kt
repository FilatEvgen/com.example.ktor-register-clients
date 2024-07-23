package example.com

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream


object FirebaseAdmin {
    val token = System.getenv("TOKEN")
    private val serviceAccount: FileInputStream =
        FileInputStream(token)

    private val options: FirebaseOptions = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build()

    fun init(): FirebaseApp = FirebaseApp.initializeApp(options)
}
