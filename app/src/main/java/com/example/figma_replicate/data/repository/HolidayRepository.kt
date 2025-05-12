package com.example.figma_replicate.data.repository

import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.AddHoliday
import com.example.figma_replicate.data.models.Holiday
import com.example.figma_replicate.data.network.ApiServiceInterface
import javax.inject.Inject

class HolidayRepository @Inject constructor(
    private val apiService: ApiServiceInterface,
    private val authPrefs: AuthPrefs
) {
    suspend fun fetchHoliday(): List<Holiday> {
        val token = authPrefs.getToken()
        return apiService.fetchHoliday("Bearer $token")
    }

    suspend fun addHoliday(token: String, holiday: AddHoliday): Holiday {
        return apiService.addHoliday(token, holiday)
    }
}