package com.example.figma_replicate.data.repository
//
//import com.example.figma_replicate.Domain.Repository.Repository
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.network.ApiServiceInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
class SignupRepository @Inject constructor(
    private val apiService: ApiServiceInterface
) {
    suspend fun signup(user: User) = apiService.signup(user)
}
