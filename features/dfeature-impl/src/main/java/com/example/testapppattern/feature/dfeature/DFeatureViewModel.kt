package com.example.testapppattern.feature.dfeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapppattern.feature.main.domain.usecase.GetMainScreenViewsCountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DFeatureUiState(
    val mainScreenViewsCount: Int = 0,
)

class DFeatureViewModel @Inject constructor(
    private val getMainScreenViewsCountUseCase: GetMainScreenViewsCountUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DFeatureUiState())
    val uiState: StateFlow<DFeatureUiState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val count = getMainScreenViewsCountUseCase()
            _uiState.update { it.copy(mainScreenViewsCount = count) }
        }
    }
}
