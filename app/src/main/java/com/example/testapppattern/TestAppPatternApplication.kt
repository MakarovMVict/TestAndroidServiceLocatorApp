package com.example.testapppattern

import android.app.Application
import com.example.testapppattern.core.di.feature.FeatureKeys
import com.example.testapppattern.core.di.feature.FeatureServiceLocator
import com.example.testapppattern.core.navigation.AppFeatureNavGraph
import com.example.testapppattern.di.DaggerAppComponent
import com.example.testapppattern.feature.main.di.MainIFeatureComponentFactory
import com.example.testapppattern.feature.main.navigation.MainFeatureNavGraphImpl
import com.example.testapppattern.feature.payments.navigation.PaymentsFeatureNavGraphImpl
import com.example.testapppattern.feature.settings.di.SettingsIFeatureComponentFactory
import com.example.testapppattern.feature.settings.navigation.SettingsFeatureNavGraphImpl

class TestAppPatternApplication : Application() {

    private val mainFeatureNavGraph = MainFeatureNavGraphImpl()
    private val paymentsFeatureNavGraph = PaymentsFeatureNavGraphImpl()
    private val settingsFeatureNavGraph = SettingsFeatureNavGraphImpl()

    val featureNavGraphs: List<AppFeatureNavGraph>
        get() = listOf(mainFeatureNavGraph, paymentsFeatureNavGraph, settingsFeatureNavGraph)

    private val appComponent by lazy(LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        FeatureServiceLocator.registerFactoryCreator(FeatureKeys.MAIN) {
            MainIFeatureComponentFactory(
                appContext = this,
                featureKey = FeatureKeys.MAIN,
                featureServiceLocator = FeatureServiceLocator
            )
        }
        FeatureServiceLocator.registerFactoryCreator(FeatureKeys.SETTINGS) {
            SettingsIFeatureComponentFactory(
                featureKey = FeatureKeys.SETTINGS,
                featureServiceLocator = FeatureServiceLocator,
                appDependencies = appComponent,
            )
        }
    }
}
