package com.example.testapppattern.feature.main.graph

import androidx.lifecycle.ViewModel
import com.example.testapppattern.core.di.feature.FeatureKeys
import com.example.testapppattern.core.di.feature.FeatureServiceLocator
import com.example.testapppattern.feature.main.di.MainFeatureGraph

class MainGraphHolderViewModel : ViewModel() {

    val component: MainFeatureGraph by lazy(LazyThreadSafetyMode.NONE) {
        FeatureServiceLocator
            .getFactory(FeatureKeys.MAIN)
            .getComponent() as MainFeatureGraph
    }

    override fun onCleared() {
        FeatureServiceLocator.removeFactory(key = FeatureKeys.MAIN, hardRemove = false)
        super.onCleared()
    }
}
