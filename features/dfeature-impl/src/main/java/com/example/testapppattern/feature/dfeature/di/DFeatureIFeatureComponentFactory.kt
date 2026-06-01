package com.example.testapppattern.feature.dfeature.di

import com.example.testapppattern.core.di.feature.FeatureInstanceOwnerKey
import com.example.testapppattern.core.di.feature.FeatureKey
import com.example.testapppattern.core.di.feature.IFeatureComponentFactory
import java.lang.ref.WeakReference
import java.util.concurrent.ConcurrentHashMap

class DFeatureIFeatureComponentFactory(
    private val featureKey: FeatureKey,
) : IFeatureComponentFactory<DFeatureComponent> {

    override val dependenciesKeysList: Set<FeatureKey> = emptySet()
    private val featureOwners: MutableSet<FeatureInstanceOwnerKey> = ConcurrentHashMap.newKeySet()

    private var weakComponentRef: WeakReference<DFeatureComponent>? = null

    private var strongComponentRef: DFeatureComponent? = null

    override fun getComponent(ownerKey: FeatureInstanceOwnerKey): DFeatureComponent {
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

        val builtComponent = DaggerDFeatureComponent.create()

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
        val shouldClear = featureOwners.isEmpty()
        if (shouldClear) {
            strongComponentRef = null
            if (hardRemove) {
                weakComponentRef?.clear()
            }
        }

    }
}
