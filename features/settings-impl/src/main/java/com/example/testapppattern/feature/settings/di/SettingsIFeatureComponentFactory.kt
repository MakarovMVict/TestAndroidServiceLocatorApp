package com.example.testapppattern.feature.settings.di

import com.example.testapppattern.core.di.app.AppDependencies
import com.example.testapppattern.core.di.feature.FeatureFactoriesLocator
import com.example.testapppattern.core.di.feature.FeatureKeys
import com.example.testapppattern.core.di.feature.IFeatureComponentFactory
import com.example.testapppattern.feature.main.api.MainFeatureDependencies
import java.lang.ref.WeakReference

class SettingsIFeatureComponentFactory(
    private val featureKey: String,
    private val featureServiceLocator: FeatureFactoriesLocator,
    private val appDependencies: AppDependencies,
) : IFeatureComponentFactory<SettingsFeatureComponent> {

    override val dependenciesKeysList: List<String> = listOf(FeatureKeys.MAIN)

    private var weakComponentRef: WeakReference<SettingsFeatureComponent>? = null

    private var strongComponentRef: SettingsFeatureComponent? = null

    private var usageCount: Int = 0

    override fun getComponent(): SettingsFeatureComponent {
        val mainDependencies =
            featureServiceLocator.getFactory(FeatureKeys.MAIN).getComponent() as MainFeatureDependencies

        usageCount++

        if (weakComponentRef?.get() == null) {
            val component = DaggerSettingsFeatureComponent.builder()
                .appDependencies(appDependencies)
                .mainFeatureDependencies(mainDependencies)
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
