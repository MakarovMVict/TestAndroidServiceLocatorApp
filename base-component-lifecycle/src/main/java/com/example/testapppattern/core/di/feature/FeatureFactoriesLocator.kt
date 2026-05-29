package com.example.testapppattern.core.di.feature

interface FeatureFactoriesLocator {
    fun getFactory(key: FeatureKey): IFeatureComponentFactory<*>
    fun removeFactory(key: FeatureKey, ownerKey: FeatureInstanceOwnerKey, hardRemove: Boolean = true)
}
