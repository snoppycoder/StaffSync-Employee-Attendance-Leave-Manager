package com.example.figma_replicate.data

import android.content.Context
import androidx.core.content.edit
import com.example.figma_replicate.data.models.UserRole
import javax.inject.Inject

class AuthPrefs @Inject constructor(context: Context) {
    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_ROLE = "role"
        private const val KEY_REMEMBER_ME = "remember_me"
        private const val KEY_LOGIN_METHOD = "login_method"
    }

    fun saveAuthData(
        token: String?,
        userId: String,
        username: String,
        email: String?,
        role: UserRole,
    ) {
        prefs.edit {
            if (token != null) putString(KEY_TOKEN, token) else remove(KEY_TOKEN)
            putString(KEY_USER_ID, userId)
            putString(KEY_USERNAME, username)
            if (email != null) putString(KEY_EMAIL, email) else remove(KEY_EMAIL)
            putString(KEY_ROLE, role.name)
        }
    }

    fun isAuthenticated(): Boolean {
        return prefs.getString(KEY_TOKEN, null) != null
    }

    fun isManager(): Boolean {
        return getUserRole() == UserRole.MANAGER
    }

    fun getUserRole(): UserRole? {
        val roleName = prefs.getString(KEY_ROLE, null)
        return roleName?.let { UserRole.valueOf(it) }
    }

    fun getUserId(): String? {
        return prefs.getString(KEY_USER_ID, null)
    }

    fun getUsername(): String? {
        return prefs.getString(KEY_USERNAME, null)
    }

    fun getEmail(): String? {
        return prefs.getString(KEY_EMAIL, null)
    }

    fun getToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }

    fun clearAuthData() {
        prefs.edit { clear() }
    }
}