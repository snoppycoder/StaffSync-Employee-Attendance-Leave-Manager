package com.example.figma_replicate.di

import android.content.Context
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.network.ApiServiceInterface
import com.example.figma_replicate.data.repository.LoginRepository
import com.example.figma_replicate.data.repository.SignupRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import java.util.concurrent.TimeUnit
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "http://192.168.100.6:3000/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServiceInterface =
        retrofit.create(ApiServiceInterface::class.java)
    @Provides
    @Singleton
    fun provideSignupRepository(apiService: ApiServiceInterface): SignupRepository {
        return SignupRepository(apiService)
    }
    @Provides
    @Singleton
    fun provideAuthPrefs(@ApplicationContext context: Context): AuthPrefs {
        return AuthPrefs(context)
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context
}