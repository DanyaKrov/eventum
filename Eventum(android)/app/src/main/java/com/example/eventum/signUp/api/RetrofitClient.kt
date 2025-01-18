import com.example.eventum.signUp.api.BasicAuthInterceptor
import com.example.eventum.signUp.api.SignUpRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8000/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor("root", "pass"))
        .build()

    val instance: SignUpRepository by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(SignUpRepository::class.java)
    }
}