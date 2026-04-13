package com.example.testapppattern.feature.main.di

import android.content.Context
import com.example.testapppattern.core.di.ApplicationContext
import com.example.testapppattern.core.di.scope.FeatureScope
import com.example.testapppattern.feature.main.HomeViewModel
import com.example.testapppattern.feature.main.api.MainFeatureDependencies
import com.example.testapppattern.feature.main.domain.usecase.GetMainScreenViewsCountUseCase
import com.example.testapppattern.feature.main.domain.usecase.RecordMainScreenViewUseCase
import com.example.testapppattern.feature.main.domain.usecase.ResetMainScreenViewsCountUseCase
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    modules = [
        MainFeatureBindingsModule::class,
    ],
)
interface MainFeatureGraph : MainFeatureDependencies {

    override fun getMainScreenViewsCountUseCase(): GetMainScreenViewsCountUseCase
    override fun recordMainScreenViewUseCase(): RecordMainScreenViewUseCase
    override fun resetMainScreenViewsCountUseCase(): ResetMainScreenViewsCountUseCase

    fun homeViewModel(): HomeViewModel

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(@ApplicationContext context: Context): Builder

        fun build(): MainFeatureGraph
    }
}
