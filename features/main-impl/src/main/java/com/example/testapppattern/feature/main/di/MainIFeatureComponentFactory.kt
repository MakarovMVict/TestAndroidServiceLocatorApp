package com.example.testapppattern.feature.main.di

import android.content.Context
import com.example.testapppattern.core.di.feature.FeatureFactoriesLocator
import com.example.testapppattern.core.di.feature.FeatureInstanceOwnerKey
import com.example.testapppattern.core.di.feature.FeatureKey
import com.example.testapppattern.feature.main.api.MainFeatureDependencies
import com.example.testapppattern.core.di.feature.IFeatureComponentFactory
import java.lang.ref.WeakReference
import java.util.concurrent.ConcurrentHashMap

class MainIFeatureComponentFactory(
    private val appContext: Context,
    private val featureKey: FeatureKey,
    private val featureServiceLocator: FeatureFactoriesLocator
) : IFeatureComponentFactory<MainFeatureDependencies> {

    override val dependenciesKeysList: Set<FeatureKey> =
        ConcurrentHashMap.newKeySet<FeatureKey>().apply { add(FeatureKey.SETTINGS) }
    private val featureOwners: MutableSet<FeatureInstanceOwnerKey> = ConcurrentHashMap.newKeySet()

    private var weakComponentRef: WeakReference<MainFeatureDependencies>? = null

    private var strongComponentRef: MainFeatureDependencies? = null

    override fun getComponent(ownerKey: FeatureInstanceOwnerKey): MainFeatureDependencies {
        if (featureOwners.contains(ownerKey).not()) {
            featureOwners.add(ownerKey)
        }

        strongComponentRef?.let {
            return it
        }
        weakComponentRef?.get()?.let { cached ->
            strongComponentRef = cached
            return cached
        }

        val builtComponent = DaggerMainFeatureGraph.builder()
            .applicationContext(appContext)
            .featureFactoriesLocator(featureServiceLocator)
            .featureOwnerKey(ownerKey)
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
