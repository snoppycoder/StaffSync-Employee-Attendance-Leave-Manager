package com.example.figma_replicate.data.repository

import android.content.Context
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.LoginRequest
import com.example.figma_replicate.data.models.LoginResponse
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.network.ApiServiceInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ManagerRepository @Inject constructor(
    private val apiService: ApiServiceInterface,
    private val authPrefs: AuthPrefs
) {
    suspend fun fetchManager() : List<User> {
        val token = authPrefs.getToken()
        return apiService.fetchEmployee("Bearer $token")
    }
}