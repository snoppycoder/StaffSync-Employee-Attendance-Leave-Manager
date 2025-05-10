//package com.example.figma_replicate.SessionManagement
//
//import android.app.Application
//import androidx.compose.runtime.*
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.launch
//import org.json.JSONObject
//import android.util.Base64
//import dagger.hilt.android.lifecycle.HiltViewModel
//
//
//fun isJwtExpired(jwt: String): Boolean {
//    try {
//        val parts = jwt.split(".")
//        if (parts.size != 3) return true
//
//        val payload = String(Base64.decode(parts[1], Base64.URL_SAFE))
//        val json = JSONObject(payload)
//        val exp = json.getLong("exp")
//        val now = System.currentTimeMillis() / 1000
//
//        return exp < now
//    } catch (e: Exception) {
//        return true
//    }
//}
//@HiltViewModel
//
//class SessionViewModel(application: Application): AndroidViewModel(application) {
//
//
//    init {
//        viewModelScope.launch {
//            val token = getToken(context)
//            _isLoggedIn.value = token != null && !isJwtExpired(token)
//
//        }
//
//    }
//}