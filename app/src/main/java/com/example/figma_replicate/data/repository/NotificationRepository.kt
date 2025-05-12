//package com.example.figma_replicate.data.repository
//
//import com.example.figma_replicate.data.AuthPrefs
//import com.example.figma_replicate.data.models.LoginRequest
//import com.example.figma_replicate.data.models.LoginResponse
//import com.example.figma_replicate.data.models.Notification
//import com.example.figma_replicate.data.network.ApiServiceInterface
//import javax.inject.Inject
//
//class NotificationRepository @Inject constructor(
//    private val apiService: ApiServiceInterface,
//    private val authPrefs: AuthPrefs
//) {
//    suspend fun fetchNotification(): Notification {
//        val token = authPrefs.getToken()
//        return apiService.fetchNotification("Bearer $token")
//    }
//}