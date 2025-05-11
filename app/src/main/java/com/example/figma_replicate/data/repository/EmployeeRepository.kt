package com.example.figma_replicate.data.repository

import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.network.ApiServiceInterface
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val apiService: ApiServiceInterface,
    private val authPrefs: AuthPrefs
) {
    suspend fun fetchEmployee() : List<User> {
        val token = authPrefs.getToken()
        return apiService.fetchEmployee("Bearer $token")
    }
    val id:Int? = authPrefs.getUserId()?.toInt()
    suspend fun fetchInfo(): User{

        return apiService.fetchInfo(id)
    }
}