package com.example.figma_replicate.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:3000/"

    // Configure OkHttpClient with timeouts
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)  // Wait up to 30s to establish a connection
        .readTimeout(30, TimeUnit.SECONDS)     // Wait up to 30s for data to be read
        .writeTimeout(30, TimeUnit.SECONDS)    // Wait up to 30s to write data
        .build()

    // Configure Json for serialization
    private val json = Json {
        ignoreUnknownKeys = true       // Ignore extra or missing fields
        coerceInputValues = true       // Optional: Default primitives if null
    }

    // Build Retrofit instance
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    // Expose the ApiServiceInterface
    val apiService: ApiServiceInterface by lazy {
        retrofit.create(ApiServiceInterface::class.java)
    }
}