package com.example.figma_replicate.di

import com.example.figma_replicate.data.network.ApiServiceInterface
import com.example.figma_replicate.data.repository.SignupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApiService(): ApiServiceInterface {
        return Retrofit.Builder()
            .baseUrl("https://")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiServiceInterface::class.java)
    }

    @Provides
    fun provideSignupRepository(apiService: ApiServiceInterface): SignupRepository {
        return SignupRepository(apiService)
    }
}
