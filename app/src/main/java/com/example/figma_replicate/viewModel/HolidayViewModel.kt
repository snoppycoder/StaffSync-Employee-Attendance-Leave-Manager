package com.example.figma_replicate.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.models.AddHoliday
import com.example.figma_replicate.data.models.Holiday
import com.example.figma_replicate.data.repository.HolidayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String


@HiltViewModel
class HolidayViewModel @Inject constructor(
    private val holidayRepository: HolidayRepository,): ViewModel()

{

    private val _holidays = MutableStateFlow<List<Holiday>>(emptyList())
    val holidays: MutableStateFlow<List<Holiday>> = _holidays

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchHolidays()
    }

    private fun fetchHolidays() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val holidayList = holidayRepository.fetchHoliday()
                _holidays.value = holidayList
                _errorMessage.value = null
            } catch (e: Exception) {
                Log.e("HolidayViewModel", "Failed to fetch Holiday", e)
                _errorMessage.value = "Failed to load holiday: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun addHoliday(token: String, addHoliday: AddHoliday) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val newHoliday = holidayRepository.addHoliday(token, addHoliday)
                _holidays.value = listOf(newHoliday)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to add holiday: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}