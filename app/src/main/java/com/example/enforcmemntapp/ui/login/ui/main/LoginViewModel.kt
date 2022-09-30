package com.example.enforcmemntapp.ui.login.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enforcmemntapp.R
import com.example.enforcmemntapp.data.source.local.preference.PreferenceRepository
import com.example.enforcmemntapp.data.source.local.repository.UserDataRepository
import com.example.enforcmemntapp.data.source.remote.models.LoginResponse
import com.example.enforcmemntapp.data.source.remote.models.Parkingstreet
import com.example.enforcmemntapp.data.source.remote.repository.EnforcementRepository
import com.example.enforcmemntapp.data.source.toUserData
import com.example.enforcmemntapp.utils.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUIState(
    val loading: Boolean = false,
    val message: Int? = null,
    val navigate: Boolean = false,
    val loginResponse: LoginResponse? = null,
    val list: List<Parkingstreet>?=null
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val enforcementRepository: EnforcementRepository,
    private val userDataRepository: UserDataRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState = _uiState.asStateFlow()


    init {
        getParkingStreet()
    }

    private fun getParkingStreet() {
        viewModelScope.launch {
            val response = enforcementRepository.getParkingStreet()
            when(response.status){
                ApiStatus.SUCCESS->{
                    if (response.data != null){
                      val streets =  response.data.response_data.parkingstreet
                        _uiState.update {
                            it.copy(
                                loading = false,
                                navigate = false,
                                list = streets
                            )
                        }
                    }else{
                        _uiState.update {
                            it.copy(
                                loading = false,
                                navigate = false,
                                list = null,
                                message = R.string.no_streets
                            )
                        }
                    }
                }
                else->{
                    _uiState.update { it.copy(loading = false, message = response.message) }
                }
            }
        }
    }

    fun login(email: String, password: String) {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            val resource = enforcementRepository.login(email, password)

            when (resource.status) {
                ApiStatus.SUCCESS -> {
                    resource.data?.toUserData()?.let { userDataRepository.insertUserData(it).let { long-> preferenceRepository.insertUserId(long.toInt())} }
                    _uiState.update {
                        it.copy(
                            loading = false,
                            loginResponse = resource.data,
                            navigate = true
                        )
                    }
                }
                else -> {
                    _uiState.update { it.copy(loading = false, message = resource.message) }
                }
            }
        }
    }

    fun hasNavigated() {
        _uiState.update { it.copy(navigate = false) }
    }

    fun setStreet(s: String) {
        viewModelScope.launch {
            preferenceRepository.setStreet(s)
        }
    }
}