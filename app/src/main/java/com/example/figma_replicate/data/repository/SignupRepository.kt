package com.example.figma_replicate.data.repository
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.network.ApiServiceInterface
import javax.inject.Inject

class SignupRepository @Inject constructor(
    private val apiService: ApiServiceInterface
) {
    suspend fun signup(user: User):User {
        return apiService.signup(user)
    }
}