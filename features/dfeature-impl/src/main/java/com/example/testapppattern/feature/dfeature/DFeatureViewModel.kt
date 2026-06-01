package com.example.testapppattern.feature.dfeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapppattern.feature.dfeature.domain.repository.IDFeatureScreenViewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DFeatureUiState(
    val viewsCount: Int = 0,
)

class DFeatureViewModel @Inject constructor(
    private val repository: IDFeatureScreenViewsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DFeatureUiState())
    val uiState: StateFlow<DFeatureUiState> = _uiState.asStateFlow()

    fun onScreenShown() {
        viewModelScope.launch {
            val updatedCount = repository.incrementAndGet()
            _uiState.update { it.copy(viewsCount = updatedCount) }
        }
    }
}
