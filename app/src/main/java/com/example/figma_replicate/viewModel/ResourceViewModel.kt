package com.example.figma_replicate.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResourceViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val authPrefs: AuthPrefs
): ViewModel()


{
    private val _employee = MutableStateFlow<User?>(null)
    val employee: StateFlow<User?> = _employee
        init {
            fetch()
        }



    private fun fetch() {
        viewModelScope.launch {
            try {
                val user = employeeRepository.fetchInfo()
                _employee.value = user
            }
            catch(e:Exception){
                println("here is the error $e")
            }

        }
    }



}