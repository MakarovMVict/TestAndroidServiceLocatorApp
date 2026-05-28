package com.example.testapppattern.feature.settings.domain.usecase

import com.example.testapppattern.feature.settings.domain.repository.ISettingsDetailLevelRepository
import javax.inject.Inject

internal class UpdateSettingsDetailLevelUseCaseImpl @Inject constructor(
    private val repository: ISettingsDetailLevelRepository,
) : UpdateSettingsDetailLevelUseCase {
    override fun invoke(level: String) {
        repository.setDetailLevel(level)
    }
}
