package com.example.testapppattern.feature.settings.domain.usecase

interface GetSettingsDetailLevelUseCase {
    suspend operator fun invoke(): String
}
