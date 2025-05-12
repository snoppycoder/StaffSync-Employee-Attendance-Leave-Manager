package com.example.figma_replicate.di

import android.content.Context
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.network.ApiServiceInterface
import com.example.figma_replicate.data.repository.AttendanceRepository
import com.example.figma_replicate.data.repository.EmployeeRepository
import com.example.figma_replicate.data.repository.LeaveRepository
import com.example.figma_replicate.data.repository.LoginRepository
import com.example.figma_replicate.data.repository.ManagerRepository
import com.example.figma_replicate.data.repository.SignupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "http://192.168.252.2:3000/" // Replace with your API URL

    @Provides
    @Singleton
    fun provideOkHttpClient(authPrefs: AuthPrefs): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val token = authPrefs.getToken()
                val requestBuilder = chain.request().newBuilder()
                if (token != null) {
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }
                chain.proceed(requestBuilder.build())
            }
            .build()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
        encodeDefaults = true
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
    fun provideLoginRepository(apiService: ApiServiceInterface): LoginRepository {
        return LoginRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideSignupRepository(apiService: ApiServiceInterface): SignupRepository {
        return SignupRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideAttendanceRepository(apiService: ApiServiceInterface): AttendanceRepository {
        return AttendanceRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideAuthPrefs(@ApplicationContext context: Context): AuthPrefs {
        return AuthPrefs(context)
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun providesEmployeeRepository(apiService: ApiServiceInterface,
                                   authPrefs: AuthPrefs
    ): EmployeeRepository {
        return EmployeeRepository(apiService, authPrefs)
    }
    @Provides
    @Singleton
    fun providesManagerRepository(apiService: ApiServiceInterface,
                                  authPrefs: AuthPrefs
    ): ManagerRepository {
        return ManagerRepository(apiService, authPrefs)
    }

    @Provides
    @Singleton
    fun provideLeaveRepository(apiService: ApiServiceInterface, authPrefs: AuthPrefs): LeaveRepository {
        return LeaveRepository(apiService, authPrefs)
    }


}