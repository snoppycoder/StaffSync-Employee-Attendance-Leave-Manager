package com.example.figma_replicate.data

import android.content.Context
import androidx.core.content.edit
import com.example.figma_replicate.data.models.UserRole

class AuthPrefs(context: Context) {
    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USERNAME = "username"
        private const val KEY_ROLE = "role"
        private const val KEY_REMEMBER_ME = "remember_me"
        private const val KEY_LOGIN_METHOD = "login_method"
    }

    // Save authentication data after login or signup
    fun saveAuthData(
        token: String?,
        userId: String,
        username: String,
        role: UserRole
    ) {
        prefs.edit {
            if (token != null) putString(KEY_TOKEN, token) else remove(KEY_TOKEN)
            putString(KEY_USER_ID, userId)
            putString(KEY_USERNAME, username)
            putString(KEY_ROLE, role.name)
        }
    }

    // Check if user is authenticated (has a valid token)
    fun isAuthenticated(): Boolean {
        return prefs.getString(KEY_TOKEN, null) != null
    }


    // Get user role
    fun getUserRole(): UserRole? {
        val roleName = prefs.getString(KEY_ROLE, null)
        return roleName?.let { UserRole.valueOf(it) }
    }

    // Get user ID
    fun getUserId(): String? {
        return prefs.getString(KEY_USER_ID, null)
    }

    // Get username
    fun getUsername(): String? {
        return prefs.getString(KEY_USERNAME, null)
    }

    // Get token
    fun getToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }



    // Clear all authentication data (e.g., on logout)
    fun clearAuthData() {
        prefs.edit { clear() }
    }

    fun isManager(): Boolean {
        return getUserRole() == UserRole.MANAGER
    }
}