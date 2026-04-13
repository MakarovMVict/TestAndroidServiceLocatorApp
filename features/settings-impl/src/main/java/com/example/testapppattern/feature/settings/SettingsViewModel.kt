package com.example.testapppattern.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapppattern.core.di.app.AGlobal
import com.example.testapppattern.feature.main.domain.usecase.GetMainScreenViewsCountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val notificationsEnabled: Boolean = true,
    val mainScreenViewsCount: Int = 0,
    val appStartedAtMillis: Long = 0,
)

class SettingsViewModel @Inject constructor(
    private val getMainScreenViewsCountUseCase: GetMainScreenViewsCountUseCase,
    private val aGlobal: AGlobal,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SettingsUiState(appStartedAtMillis = aGlobal.startedAtMillis),
    )
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun onNotificationsToggle(enabled: Boolean) {
        _uiState.update { it.copy(notificationsEnabled = enabled) }
    }

    fun refresh() {
        viewModelScope.launch {
            val count = getMainScreenViewsCountUseCase()
            _uiState.update { it.copy(mainScreenViewsCount = count) }
        }
    }
}
