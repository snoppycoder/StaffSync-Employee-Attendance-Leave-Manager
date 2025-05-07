package com.example.figma_replicate.data.network


import com.example.figma_replicate.domain.model.Attendance
import com.example.figma_replicate.domain.model.LeaveRequest
import com.example.figma_replicate.domain.model.User
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

private const val BASE_URL =
    "http://10.0.2.2:3000/"


private val client = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)  // wait up to 30s to establish a connection
    .readTimeout(30, TimeUnit.SECONDS)     // wait up to 30s for data to be read
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()
/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */

private val json = Json {
    ignoreUnknownKeys = true       // ignore extra or missing fields
    coerceInputValues = true       // optional: default primitives if null
}

private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()


/**
 * Retrofit service object for creating api calls
 */
interface ApiServiceInterface {
    @GET("/api/users")
    suspend fun getUsers(): List<User>
    @POST("api//signup")
    suspend fun signup(@Body user: User): Response<User>

    @POST("api//login")
    suspend fun login(@Body credentials: Map<String, String>): Response<User>

    @POST("api/leaveRequest")
    suspend fun requestLeave(@Body leaveRequest: LeaveRequest): Response<LeaveRequest>

    @POST("api/leave/approve")
    suspend fun approveLeave(@Body request: Map<String, Any>): Response<Void>

    @POST("api/attendance/mark")
    suspend fun markAttendance(@Body attendance: Attendance): Response<Attendance>

    @GET("api/attendance/user/{userId}")
    suspend fun getAttendanceByUser(@Path("userId") userId: Int): Response<List<Attendance>>


}
//
//interface ApiService {

//}

object ApiService {
    val retrofitService: ApiServiceInterface by lazy {
        retrofit.create(ApiServiceInterface::class.java)
    }
}

