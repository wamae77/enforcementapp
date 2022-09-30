package com.example.enforcmemntapp.ui.business

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.enforcmemntapp.data.source.models.BusinessesDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class BusinessUiState(
    val isLoading: Boolean = false,
    val message: Int? = null,
    val businessesDataModel: BusinessesDataModel? = null

)

@HiltViewModel
class BusinessViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(BusinessUiState())
    val uiState = _uiState.asStateFlow()


    fun setArguments(data: BusinessesDataModel?) {
        _uiState.update { it.copy(businessesDataModel = data) }
    }
}