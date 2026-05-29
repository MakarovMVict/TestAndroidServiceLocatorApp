package com.example.testapppattern.feature.settings.di

import com.example.testapppattern.core.di.app.AppDependencies
import com.example.testapppattern.core.di.feature.FeatureFactoriesLocator
import com.example.testapppattern.core.di.feature.FeatureInstanceOwnerKey
import com.example.testapppattern.core.di.feature.FeatureKey
import com.example.testapppattern.core.di.feature.IFeatureComponentFactory
import com.example.testapppattern.feature.main.api.MainFeatureDependencies
import java.lang.ref.WeakReference
import java.util.concurrent.ConcurrentHashMap

class SettingsIFeatureComponentFactory(
    private val featureKey: FeatureKey,
    private val featureServiceLocator: FeatureFactoriesLocator,
    private val appDependencies: AppDependencies,
) : IFeatureComponentFactory<SettingsFeatureComponent> {

    override val dependenciesKeysList: Set<FeatureKey> =
        ConcurrentHashMap.newKeySet<FeatureKey>().apply { add(FeatureKey.MAIN) }
    private val featureOwners: MutableSet<FeatureInstanceOwnerKey> = ConcurrentHashMap.newKeySet()

    private var weakComponentRef: WeakReference<SettingsFeatureComponent>? = null

    private var strongComponentRef: SettingsFeatureComponent? = null

    override fun getComponent(ownerKey: FeatureInstanceOwnerKey): SettingsFeatureComponent {
        if (featureOwners.contains(ownerKey).not()) {
            featureOwners.add(ownerKey)
        }

        val mainDependencies =
            featureServiceLocator.getFactory(FeatureKey.MAIN)
                .getComponent(ownerKey = ownerKey) as MainFeatureDependencies

        strongComponentRef?.let {
            return it
        }
        weakComponentRef?.get()?.let { cached ->
            strongComponentRef = cached
            return cached
        }

        val builtComponent = DaggerSettingsFeatureComponent.builder()
            .appDependencies(appDependencies)
            .mainFeatureDependencies(mainDependencies)
            .build()

        if (weakComponentRef?.get() == null) {
            weakComponentRef = WeakReference(builtComponent)
        }
        strongComponentRef = weakComponentRef?.get() ?: builtComponent
        return strongComponentRef!!
    }

    override fun removeComponent(
        featureKey: FeatureKey,
        ownerKey: FeatureInstanceOwnerKey,
        hardRemove: Boolean
    ) {
        require(featureKey == this.featureKey) {
            "Expected featureKey=$this.featureKey, got $featureKey"
        }

        if (featureOwners.remove(ownerKey).not()) {
            return
        }
        val dependencies = dependenciesKeysList.toList()
        val shouldClear = featureOwners.isEmpty()
        if (shouldClear) {
            strongComponentRef = null
            if (hardRemove) {
                weakComponentRef?.clear()
            }
        }

        for (key in dependencies) {
            featureServiceLocator.removeFactory(
                key = key,
                ownerKey = ownerKey,
                hardRemove = hardRemove,
            )
        }
    }
}
