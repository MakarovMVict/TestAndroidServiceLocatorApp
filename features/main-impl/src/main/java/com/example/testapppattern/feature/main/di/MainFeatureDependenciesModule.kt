package com.example.testapppattern.feature.main.di

import com.example.testapppattern.core.di.feature.FeatureFactoriesLocator
import com.example.testapppattern.core.di.feature.FeatureInstanceOwnerKey
import com.example.testapppattern.core.di.feature.FeatureKey
import com.example.testapppattern.feature.settings.api.SettingsFeatureDependencies
import com.example.testapppattern.feature.settings.domain.usecase.GetSettingsDetailLevelUseCase
import dagger.Module
import dagger.Provides

@Module
internal class MainFeatureDependenciesModule {

    @Provides
    fun provideSettingsFeatureDependencies(
        featureFactoriesLocator: FeatureFactoriesLocator,
        featureOwnerKey: FeatureInstanceOwnerKey,
    ): SettingsFeatureDependencies {
        return featureFactoriesLocator
            .getFactory(FeatureKey.SETTINGS)
            .getComponent(ownerKey = featureOwnerKey) as SettingsFeatureDependencies
    }

    @Provides
    fun provideGetSettingsDetailLevelUseCase(
        settingsFeatureDependencies: SettingsFeatureDependencies,
    ): GetSettingsDetailLevelUseCase {
        return settingsFeatureDependencies.getSettingsDetailLevelUseCase()
    }
}
