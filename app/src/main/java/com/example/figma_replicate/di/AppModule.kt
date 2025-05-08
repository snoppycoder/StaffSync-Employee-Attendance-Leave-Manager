package com.example.figma_replicate.di

import com.example.figma_replicate.data.network.ApiService
import com.example.figma_replicate.data.repository.SignupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://your.api.base.url")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideSignupRepository(apiService: ApiService): SignupRepository {
        return SignupRepository(apiService)
    }
}
