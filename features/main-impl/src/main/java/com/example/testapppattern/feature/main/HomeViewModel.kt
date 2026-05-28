package com.example.testapppattern.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapppattern.feature.main.domain.usecase.GetMainScreenViewsCountUseCase
import com.example.testapppattern.feature.main.domain.usecase.RecordMainScreenViewUseCase
import com.example.testapppattern.feature.settings.domain.usecase.GetSettingsDetailLevelUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val visitCount: Int = 0,
    val settingsDetailLevel: String = "",
)

class HomeViewModel @Inject constructor(
    private val getMainScreenViewsCountUseCase: GetMainScreenViewsCountUseCase,
    private val recordMainScreenViewUseCase: RecordMainScreenViewUseCase,
    private val getSettingsDetailLevelUseCase: GetSettingsDetailLevelUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onScreenShown() {
        viewModelScope.launch {
            val detailLevel = getSettingsDetailLevelUseCase()
            // Состояние всегда берём из репозитория через use-case.
            val current = getMainScreenViewsCountUseCase()
            _uiState.update {
                it.copy(visitCount = current, settingsDetailLevel = detailLevel)
            }

            // Затем фиксируем очередной показ главного экрана.
            recordMainScreenViewUseCase()

            val updated = getMainScreenViewsCountUseCase()
            _uiState.update { it.copy(visitCount = updated) }
        }
    }
}
