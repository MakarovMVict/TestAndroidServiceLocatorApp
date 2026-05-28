package com.example.testapppattern.feature.settings.di

import com.example.testapppattern.feature.settings.data.SettingsDetailLevelRepositoryImpl
import com.example.testapppattern.feature.settings.domain.repository.ISettingsDetailLevelRepository
import com.example.testapppattern.feature.settings.domain.usecase.GetSettingsDetailLevelUseCase
import com.example.testapppattern.feature.settings.domain.usecase.GetSettingsDetailLevelUseCaseImpl
import com.example.testapppattern.feature.settings.domain.usecase.UpdateSettingsDetailLevelUseCase
import com.example.testapppattern.feature.settings.domain.usecase.UpdateSettingsDetailLevelUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal interface SettingsFeatureBindingsModule {

    @Binds
    fun bindSettingsDetailLevelRepository(
        impl: SettingsDetailLevelRepositoryImpl,
    ): ISettingsDetailLevelRepository

    @Binds
    fun bindGetSettingsDetailLevelUseCase(
        impl: GetSettingsDetailLevelUseCaseImpl,
    ): GetSettingsDetailLevelUseCase

    @Binds
    fun bindUpdateSettingsDetailLevelUseCase(
        impl: UpdateSettingsDetailLevelUseCaseImpl,
    ): UpdateSettingsDetailLevelUseCase
}
