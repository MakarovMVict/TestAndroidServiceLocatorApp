package com.example.testapppattern.feature.settings.graph

import androidx.lifecycle.ViewModel
import com.example.testapppattern.core.di.feature.FeatureKey
import com.example.testapppattern.core.di.feature.FeatureOwnerKeyRegistry
import com.example.testapppattern.core.di.feature.FeatureServiceLocator
import com.example.testapppattern.feature.settings.di.SettingsFeatureComponent

class SettingsGraphHolderViewModel : ViewModel() {

    private val ownerKey = FeatureOwnerKeyRegistry.nextOwnerKey(FeatureKey.SETTINGS)

    val component: SettingsFeatureComponent by lazy(LazyThreadSafetyMode.NONE) {
        FeatureServiceLocator
            .getFactory(FeatureKey.SETTINGS)
            .getComponent(ownerKey = ownerKey) as SettingsFeatureComponent
    }

    override fun onCleared() {
        FeatureServiceLocator.removeFactory(
            key = FeatureKey.SETTINGS,
            ownerKey = ownerKey,
            hardRemove = true,
        )
        super.onCleared()
    }
}
