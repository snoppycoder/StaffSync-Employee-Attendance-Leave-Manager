package com.example.figma_replicate.data.repository

import com.example.figma_replicate.data.models.LoginUsers
import com.example.figma_replicate.data.network.ApiServiceInterface
import javax.inject.Inject

class LoginRepository @Inject() constructor(
        private val apiService: ApiServiceInterface
    )
    {
        suspend fun login(user: LoginUsers) = apiService.login(user)
    }
