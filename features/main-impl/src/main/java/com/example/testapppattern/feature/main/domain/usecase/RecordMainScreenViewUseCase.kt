package com.example.testapppattern.feature.main.domain.usecase

import com.example.testapppattern.feature.main.domain.repository.IMainScreenRepository
import com.example.testapppattern.feature.main.domain.usecase.RecordMainScreenViewUseCase
import javax.inject.Inject

internal class RecordMainScreenViewUseCaseImpl @Inject constructor(
    private val repository: IMainScreenRepository,
) : RecordMainScreenViewUseCase {
    override suspend operator fun invoke() {
        val current = repository.getViewsCount()
        repository.setViewsCount(current + 1)
    }
}

