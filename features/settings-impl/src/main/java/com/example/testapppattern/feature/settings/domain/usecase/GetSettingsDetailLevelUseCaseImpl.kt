package com.example.testapppattern.feature.settings.domain.usecase

import com.example.testapppattern.feature.settings.domain.repository.ISettingsDetailLevelRepository
import com.example.testapppattern.feature.settings.domain.usecase.GetSettingsDetailLevelUseCase
import javax.inject.Inject

internal class GetSettingsDetailLevelUseCaseImpl @Inject constructor(
    private val repository: ISettingsDetailLevelRepository,
) : GetSettingsDetailLevelUseCase {
    override suspend operator fun invoke(): String = repository.getDetailLevel()
}
