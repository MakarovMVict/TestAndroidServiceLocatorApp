package com.example.testapppattern.feature.payments

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PaymentsUiState(
    val pendingCount: Int = 0,
)

class PaymentsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PaymentsUiState(pendingCount = 3))
    val uiState: StateFlow<PaymentsUiState> = _uiState.asStateFlow()

    fun acknowledgePending() {
        _uiState.update { it.copy(pendingCount = 0) }
    }
}
