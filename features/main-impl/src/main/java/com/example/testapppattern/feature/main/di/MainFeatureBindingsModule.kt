package com.example.testapppattern.feature.main.di

import com.example.testapppattern.feature.main.data.MainScreenViewsRepositoryImpl
import com.example.testapppattern.feature.main.domain.repository.IMainScreenRepository
import com.example.testapppattern.feature.main.domain.usecase.GetMainScreenViewsCountUseCase
import com.example.testapppattern.feature.main.domain.usecase.RecordMainScreenViewUseCase
import com.example.testapppattern.feature.main.domain.usecase.ResetMainScreenViewsCountUseCase
import com.example.testapppattern.feature.main.domain.usecase.GetMainScreenViewsCountUseCaseImpl
import com.example.testapppattern.feature.main.domain.usecase.RecordMainScreenViewUseCaseImpl
import com.example.testapppattern.feature.main.domain.usecase.ResetMainScreenViewsCountUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal interface MainFeatureBindingsModule {

    @Binds
    fun bindMainScreenRepository(impl: MainScreenViewsRepositoryImpl): IMainScreenRepository

    @Binds
    fun bindGetMainScreenViewsCountUseCase(impl: GetMainScreenViewsCountUseCaseImpl): GetMainScreenViewsCountUseCase

    @Binds
    fun bindRecordMainScreenViewUseCase(impl: RecordMainScreenViewUseCaseImpl): RecordMainScreenViewUseCase

    @Binds
    fun bindResetMainScreenViewsCountUseCase(
        impl: ResetMainScreenViewsCountUseCaseImpl,
    ): ResetMainScreenViewsCountUseCase
}

