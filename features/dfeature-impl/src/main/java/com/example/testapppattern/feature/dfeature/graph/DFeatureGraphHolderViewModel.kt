package com.example.testapppattern.feature.dfeature.graph

import androidx.lifecycle.ViewModel
import com.example.testapppattern.core.di.feature.FeatureKey
import com.example.testapppattern.core.di.feature.FeatureOwnerKeyRegistry
import com.example.testapppattern.core.di.feature.FeatureServiceLocator
import com.example.testapppattern.feature.dfeature.di.DFeatureComponent

class DFeatureGraphHolderViewModel : ViewModel() {

    private val ownerKey = FeatureOwnerKeyRegistry.nextOwnerKey(FeatureKey.DFEATURE)

    val component: DFeatureComponent by lazy(LazyThreadSafetyMode.NONE) {
        FeatureServiceLocator
            .getFactory(FeatureKey.DFEATURE)
            .getComponent(ownerKey = ownerKey) as DFeatureComponent
    }

    override fun onCleared() {
        FeatureServiceLocator.removeFactory(
            key = FeatureKey.DFEATURE,
            ownerKey = ownerKey,
            hardRemove = true,
        )
        super.onCleared()
    }
}
