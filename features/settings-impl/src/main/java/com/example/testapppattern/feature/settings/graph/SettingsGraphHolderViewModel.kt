package com.example.testapppattern.feature.settings.graph

import androidx.lifecycle.ViewModel
import com.example.testapppattern.core.di.feature.FeatureKeys
import com.example.testapppattern.core.di.feature.FeatureServiceLocator
import com.example.testapppattern.feature.settings.di.SettingsFeatureComponent

class SettingsGraphHolderViewModel : ViewModel() {

    val component: SettingsFeatureComponent by lazy(LazyThreadSafetyMode.NONE) {
        FeatureServiceLocator
            .getFactory(FeatureKeys.SETTINGS)
            .getComponent() as SettingsFeatureComponent
    }

    override fun onCleared() {
        FeatureServiceLocator.removeFactory(FeatureKeys.SETTINGS)
        super.onCleared()
    }
}
