package com.example.enforcmemntapp.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enforcmemntapp.R
import com.example.enforcmemntapp.data.source.local.preference.PreferenceRepository
import com.example.enforcmemntapp.data.source.local.repository.UserDataRepository
import com.example.enforcmemntapp.data.source.remote.models.CheckbusinessResponse
import com.example.enforcmemntapp.data.source.remote.models.ParkingResponse
import com.example.enforcmemntapp.data.source.remote.models.QueryByReceiptResponse
import com.example.enforcmemntapp.data.source.remote.repository.EnforcementRepository
import com.example.enforcmemntapp.utils.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiHomeState(
    val isLoading: Boolean = false,
    val message: Int? = null,
    val queryByReceiptResponse: QueryByReceiptResponse? = null,
    val checkbusinessResponse: CheckbusinessResponse? = null,
    val parkingResponse: ParkingResponse? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val enforcementRepository: EnforcementRepository,
    private val userDataRepository: UserDataRepository,
    private val preferenceRepository: PreferenceRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiHomeState())
    val uiState
        get() = _uiState.asStateFlow()

    init {
        getIdPref()
    }

    private fun getIdPref() {
        viewModelScope.launch {
            val id = preferenceRepository.getUserId().first()
            id?.let {
                it.let { id ->
                    savedStateHandle["userId"] = userDataRepository.getUserData(id)?.UserID
                }
            }
        }
    }

    fun queryByReceiptNumber(number: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            val userId: String? = savedStateHandle["userId"]
            userId?.let {
                val resource =
                    enforcementRepository.queryByReceiptIdNumber(number, it, latitude, longitude)
                when (resource.status) {
                    ApiStatus.SUCCESS -> {
                        if (resource.data?.status == 0) {
                            _uiState.update {
                                it.copy(
                                    message = R.string.not_found_receipt,
                                    isLoading = false
                                )
                            }
                        } else {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    queryByReceiptResponse = resource.data
                                )
                            }
                        }
                    }
                    else -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                queryByReceiptResponse = null,
                                message = resource.message
                            )
                        }
                    }
                }
            }
        }
    }

    fun messageShown() {
        _uiState.update { it.copy(message = null) }
    }

    fun businessValidity(string: String) {
        viewModelScope.launch {
            val userId: String? = savedStateHandle["userId"]
            userId?.let { id ->
                val resource = enforcementRepository.checkBusinessValidity(string, id)
                when (resource.status) {
                    ApiStatus.SUCCESS -> {

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                queryByReceiptResponse = null,
                                checkbusinessResponse = resource.data
                            )
                        }
                    }
                    else -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                queryByReceiptResponse = null,
                                message = resource.message
                            )
                        }
                    }
                }
            }
        }
    }

    fun hasNavigated() {
        _uiState.update {
            it.copy(checkbusinessResponse = null, parkingResponse = null)
        }
    }

    fun queryParkingByPlate(string: String) {
        viewModelScope.launch {
            val userId: String? = savedStateHandle["userId"]
            userId?.let {
                val resource = enforcementRepository.parking(string, it, latitude, longitude, "1")
                when (resource.status) {
                    ApiStatus.SUCCESS -> {

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                queryByReceiptResponse = null,
                                checkbusinessResponse = null,
                                parkingResponse = resource.data
                            )
                        }
                    }
                    else -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                queryByReceiptResponse = null,
                                message = resource.message
                            )
                        }
                    }
                }
            }
        }

    }

    companion object {
        private const val longitude = "35.1269"
        private const val latitude = "0.1836"
    }
}