package com.example.figma_replicate.data.network

import com.example.figma_replicate.data.models.User
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

// Base URL for API
private const val BASE_URL = "http://10.0.2.2:3000/"

// OkHttpClient for custom timeout configurations
private val client = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)  // Timeout for establishing a connection
    .readTimeout(30, TimeUnit.SECONDS)     // Timeout for reading data
    .writeTimeout(30, TimeUnit.SECONDS)    // Timeout for sending data
    .build()

// Json serializer configuration with kotlinx.serialization
private val json = Json {
    ignoreUnknownKeys = true       // Ignore extra or missing fields in the response
    coerceInputValues = true       // Convert null or missing values to default types
}

// Retrofit instance setup
private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType())) // Specify JSON converter
    .baseUrl(BASE_URL)  // Set base URL for API
    .build()

/**
 * Retrofit interface to define API endpoints
 */
interface ApiServiceInterface {

    // GET request for fetching users
    @GET("api/users")
    suspend fun getUsers(): List<User>

    // POST request for signing up a new user
    @POST("api/signup")
    suspend fun signup(@Body user: User): User
}

// Object to expose the Retrofit service instance lazily
object ApiService {
    val retrofitService: ApiServiceInterface by lazy {
        retrofit.create(ApiServiceInterface::class.java)
    }
}
