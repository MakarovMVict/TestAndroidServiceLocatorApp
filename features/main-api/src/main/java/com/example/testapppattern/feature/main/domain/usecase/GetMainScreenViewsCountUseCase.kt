package com.example.testapppattern.feature.main.domain.usecase

interface GetMainScreenViewsCountUseCase {
    suspend operator fun invoke(): Int
}

