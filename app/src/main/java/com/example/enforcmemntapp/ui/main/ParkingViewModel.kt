package com.example.enforcmemntapp.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.enforcmemntapp.data.source.models.ParkingDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ParkingUi(
    val parkingDataModel: ParkingDataModel? = null
)

@HiltViewModel
class ParkingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiState = MutableStateFlow(ParkingUi())
    val uiState get() = _uiState.asStateFlow()

    fun setArguments(data: ParkingDataModel?) {
        _uiState.update { it.copy(parkingDataModel = data) }
    }
    // TODO: Implement the ViewModel
}