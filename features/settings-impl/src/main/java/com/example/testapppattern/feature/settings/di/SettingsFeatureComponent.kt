package com.example.testapppattern.feature.settings.di

import com.example.testapppattern.core.di.app.AppDependencies
import com.example.testapppattern.core.di.scope.FeatureScope
import com.example.testapppattern.feature.main.api.MainFeatureDependencies
import com.example.testapppattern.feature.settings.ParametersViewModel
import com.example.testapppattern.feature.settings.SettingsViewModel
import com.example.testapppattern.feature.settings.api.SettingsFeatureDependencies
import com.example.testapppattern.feature.settings.domain.usecase.GetSettingsDetailLevelUseCase
import dagger.Component

@FeatureScope
@Component(
    modules = [
        SettingsFeatureBindingsModule::class,
    ],
    dependencies = [
        AppDependencies::class,
        MainFeatureDependencies::class,
    ],
)
interface SettingsFeatureComponent : SettingsFeatureDependencies {

    override fun getSettingsDetailLevelUseCase(): GetSettingsDetailLevelUseCase

    fun settingsViewModel(): SettingsViewModel

    fun parametersViewModel(): ParametersViewModel

    @Component.Builder
    interface Builder {
        fun appDependencies(appDependencies: AppDependencies): Builder

        fun mainFeatureDependencies(mainFeatureDependencies: MainFeatureDependencies): Builder

        fun build(): SettingsFeatureComponent
    }
}
