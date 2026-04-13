package com.example.testapppattern.feature.main.domain.usecase

import com.example.testapppattern.feature.main.domain.repository.IMainScreenRepository
import com.example.testapppattern.feature.main.domain.usecase.ResetMainScreenViewsCountUseCase
import javax.inject.Inject

internal class ResetMainScreenViewsCountUseCaseImpl @Inject constructor(
    private val repository: IMainScreenRepository,
) : ResetMainScreenViewsCountUseCase {
    override suspend operator fun invoke() = repository.resetViewsCount()
}

