package com.example.testapppattern.feature.main.domain.usecase

import com.example.testapppattern.feature.main.domain.repository.IMainScreenRepository
import com.example.testapppattern.feature.main.domain.usecase.GetMainScreenViewsCountUseCase
import javax.inject.Inject

internal class GetMainScreenViewsCountUseCaseImpl @Inject constructor(
    private val repository: IMainScreenRepository,
) : GetMainScreenViewsCountUseCase {
    override suspend operator fun invoke(): Int = repository.getViewsCount()
}

