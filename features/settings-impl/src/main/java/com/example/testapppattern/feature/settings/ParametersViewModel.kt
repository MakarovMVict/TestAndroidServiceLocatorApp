package com.example.testapppattern.feature.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ParametersUiState(
    val detailLevel: String = "Стандарт",
)

class ParametersViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ParametersUiState())
    val uiState: StateFlow<ParametersUiState> = _uiState.asStateFlow()

    fun onDetailLevelSelected(level: String) {
        _uiState.update { it.copy(detailLevel = level) }
    }
}
