package com.example.testapppattern.feature.settings.api

import com.example.testapppattern.feature.settings.domain.usecase.GetSettingsDetailLevelUseCase

/**
 * Публичная поверхность зависимостей фичи settings для других фич (только через settings-api).
 */
interface SettingsFeatureDependencies {
    fun getSettingsDetailLevelUseCase(): GetSettingsDetailLevelUseCase
}
