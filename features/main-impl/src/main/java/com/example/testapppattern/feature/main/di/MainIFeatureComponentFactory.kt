package com.example.testapppattern.feature.main.di

import android.content.Context
import com.example.testapppattern.core.di.feature.FeatureFactoriesLocator
import com.example.testapppattern.feature.main.api.MainFeatureDependencies
import com.example.testapppattern.core.di.feature.IFeatureComponentFactory
import java.lang.ref.WeakReference

class MainIFeatureComponentFactory(
    private val appContext: Context,
    private val featureKey: String,
    private val featureServiceLocator: FeatureFactoriesLocator
) : IFeatureComponentFactory<MainFeatureDependencies> {

    override val dependenciesKeysList: List<String> = emptyList()

    private var weakComponentRef: WeakReference<MainFeatureDependencies>? = null

    private var usageCount: Int = 0

    private var strongComponentRef = weakComponentRef?.get()

    override fun getComponent(): MainFeatureDependencies {

        for (key in dependenciesKeysList) {
            featureServiceLocator.getFactory(key).getComponent()
        }

        usageCount++

        if (weakComponentRef?.get() == null) {
            val component = DaggerMainFeatureGraph.builder()
                .applicationContext(appContext)
                .build()

            weakComponentRef = WeakReference(component)
        }

        strongComponentRef = weakComponentRef?.get()

        return strongComponentRef!!
    }

    override fun removeComponent(featureKey: String) {
        require(featureKey == this.featureKey) {
            "Expected featureKey=$this.featureKey, got $featureKey"
        }

        for (key in dependenciesKeysList) {
            featureServiceLocator.removeFactory(key)
        }

        usageCount--
        if (usageCount <= 0) {
            strongComponentRef = null
            usageCount = 0
        }
    }
}
