package com.example.testapppattern.feature.settings.di

import com.example.testapppattern.core.di.app.AppDependencies
import com.example.testapppattern.feature.main.api.MainFeatureDependencies
import com.example.testapppattern.feature.settings.ParametersViewModel
import com.example.testapppattern.feature.settings.SettingsViewModel
import dagger.Component

@Component(
    dependencies = [
        AppDependencies::class,
        MainFeatureDependencies::class,
    ],
)
interface SettingsFeatureComponent {

    fun settingsViewModel(): SettingsViewModel

    fun parametersViewModel(): ParametersViewModel

    @Component.Builder
    interface Builder {
        fun appDependencies(appDependencies: AppDependencies): Builder
        fun mainFeatureDependencies(mainFeatureDependencies: MainFeatureDependencies): Builder

        fun build(): SettingsFeatureComponent
    }
}
