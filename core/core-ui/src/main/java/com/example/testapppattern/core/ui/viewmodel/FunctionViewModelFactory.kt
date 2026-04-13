package com.example.testapppattern.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FunctionViewModelFactory<T : ViewModel>(
    private val create: () -> T,
) : ViewModelProvider.Factory {

    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
        @Suppress("UNCHECKED_CAST")
        return create() as VM
    }
}
