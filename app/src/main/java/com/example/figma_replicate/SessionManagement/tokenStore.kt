//package com.example.figma_replicate.SessionManagement
//
//import android.content.Context
//import androidx.datastore.preferences.core.*
//import androidx.datastore.preferences.core.stringPreferencesKey
//import androidx.datastore.preferences.preferencesDataStore
//import kotlinx.coroutines.flow.firstOrNull
//import kotlinx.coroutines.flow.map
//import android.content.*
//
//    val Context.dataStore by preferencesDataStore("auth_prefs")
//    suspend fun saveToken(context: Context, token: String) {
//        context.dataStore.edit {
//            it[stringPreferencesKey("jwt_token")] = token
//        }
//    }
//
//    suspend fun getToken(context: Context): String? {
//        return context.dataStore.data
//            .map { it[stringPreferencesKey("jwt_token")] }
//            .firstOrNull()
//
//
//}
