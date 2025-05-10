package com.example.figma_replicate.SessionManagement


import android.content.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
private val PREF_NAME = "session"
class SessionManager(context: Context) {
    private val appContext = context.applicationContext
    private val preference: SharedPreferences = appContext.getSharedPreferences(PREF_NAME, 0)
    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> get() = _isLoggedIn

}