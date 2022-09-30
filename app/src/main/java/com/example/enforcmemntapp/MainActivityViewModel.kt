package com.example.enforcmemntapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enforcmemntapp.data.source.local.preference.PreferenceRepository
import com.example.enforcmemntapp.data.source.local.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainHomeUiState(
    val name: String? = null,
    val message: String? = null
)

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val userDataRepository: UserDataRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainHomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserName()
    }

    private fun getUserName() {
        viewModelScope.launch {
            val id = preferenceRepository.getUserId().first()
            id?.let {
                val u = userDataRepository.getUserData(it)
                u?.let {
                   val nam =u.FirstName + "" + u.LastName
                    _uiState.update { it.copy(name = nam) }
                }
            }
        }
    }
}