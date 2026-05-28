package com.example.testapppattern.feature.main.di

import com.example.testapppattern.core.di.feature.FeatureFactoriesLocator
import com.example.testapppattern.core.di.feature.FeatureKeys
import com.example.testapppattern.feature.settings.api.SettingsFeatureDependencies
import com.example.testapppattern.feature.settings.domain.usecase.GetSettingsDetailLevelUseCase
import dagger.Module
import dagger.Provides

@Module
internal object MainSettingsBridgeModule {

    @Provides
    fun provideGetSettingsDetailLevelUseCase(
        featureFactoriesLocator: FeatureFactoriesLocator,
    ): GetSettingsDetailLevelUseCase {
        return (
            featureFactoriesLocator
                .getFactory(FeatureKeys.SETTINGS)
                .getComponent() as SettingsFeatureDependencies
            )
            .getSettingsDetailLevelUseCase()
    }
}
