package com.example.testapppattern.feature.dfeature.di

import com.example.testapppattern.feature.dfeature.data.DFeatureScreenViewsRepositoryImpl
import com.example.testapppattern.feature.dfeature.domain.repository.IDFeatureScreenViewsRepository
import dagger.Binds
import dagger.Module

@Module
internal interface DFeatureBindingsModule {
    @Binds
    fun bindDFeatureScreenViewsRepository(
        impl: DFeatureScreenViewsRepositoryImpl,
    ): IDFeatureScreenViewsRepository
}
