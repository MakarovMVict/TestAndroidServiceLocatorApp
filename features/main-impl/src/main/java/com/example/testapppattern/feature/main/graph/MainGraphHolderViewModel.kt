package com.example.testapppattern.feature.main.graph

import androidx.lifecycle.ViewModel
import com.example.testapppattern.core.di.feature.FeatureKey
import com.example.testapppattern.core.di.feature.FeatureOwnerKeyRegistry
import com.example.testapppattern.core.di.feature.FeatureServiceLocator
import com.example.testapppattern.feature.main.di.MainFeatureGraph

class MainGraphHolderViewModel : ViewModel() {

    private val ownerKey = FeatureOwnerKeyRegistry.nextOwnerKey(FeatureKey.MAIN)

    val component: MainFeatureGraph by lazy(LazyThreadSafetyMode.NONE) {
        FeatureServiceLocator
            .getFactory(FeatureKey.MAIN)
            .getComponent(ownerKey = ownerKey) as MainFeatureGraph
    }

    override fun onCleared() {
        FeatureServiceLocator.removeFactory(
            key = FeatureKey.MAIN,
            ownerKey = ownerKey,
            hardRemove = true,
        )
        super.onCleared()
    }
}
