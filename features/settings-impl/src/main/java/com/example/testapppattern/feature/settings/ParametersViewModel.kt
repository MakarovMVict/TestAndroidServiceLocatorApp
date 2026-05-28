package com.example.testapppattern.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapppattern.feature.settings.domain.usecase.GetSettingsDetailLevelUseCase
import com.example.testapppattern.feature.settings.domain.usecase.UpdateSettingsDetailLevelUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ParametersUiState(
    val detailLevel: String = "Стандарт",
)

class ParametersViewModel @Inject constructor(
    private val getSettingsDetailLevelUseCase: GetSettingsDetailLevelUseCase,
    private val updateSettingsDetailLevelUseCase: UpdateSettingsDetailLevelUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ParametersUiState())
    val uiState: StateFlow<ParametersUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val level = getSettingsDetailLevelUseCase()
            _uiState.update { it.copy(detailLevel = level) }
        }
    }

    fun onDetailLevelSelected(level: String) {
        updateSettingsDetailLevelUseCase(level)
        _uiState.update { it.copy(detailLevel = level) }
    }
}
