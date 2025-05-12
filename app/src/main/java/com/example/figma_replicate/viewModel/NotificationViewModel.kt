//package com.example.figma_replicate.viewModel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.figma_replicate.data.AuthPrefs
//import com.example.figma_replicate.data.models.Notification
//import com.example.figma_replicate.data.repository.NotificationRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class NotificationViewModel @Inject constructor(
//    private val notificationRepository: NotificationRepository,
//    private val authPrefs: AuthPrefs
//) : ViewModel() {
//
//    private val _notifications = MutableStateFlow<List<Notification>>(emptyList())
//    val notifications: StateFlow<List<Notification>> = _notifications
//
//    init {
//        fetchNotifications()
//    }
//
//    private fun fetchNotifications() {
//        viewModelScope.launch {
//            try {
//                val notificationList = notificationRepository.fetchNotification()
//                _notifications.value = listOf(notificationList)
//            } catch (e: Exception) {
//                println("Failed to fetch notifications: $e")
//            }
//        }
//    }
//}
