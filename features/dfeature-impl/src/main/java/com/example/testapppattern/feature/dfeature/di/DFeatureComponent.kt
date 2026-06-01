package com.example.testapppattern.feature.dfeature.di

import com.example.testapppattern.core.di.app.AppDependencies
import com.example.testapppattern.core.di.scope.FeatureScope
import com.example.testapppattern.feature.dfeature.DFeatureViewModel
import com.example.testapppattern.feature.dfeature.api.DFeatureDependencies
import com.example.testapppattern.feature.main.api.MainFeatureDependencies
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        AppDependencies::class,
        MainFeatureDependencies::class,
    ],
)
interface DFeatureComponent : DFeatureDependencies {

    fun dFeatureViewModel(): DFeatureViewModel

    @Component.Builder
    interface Builder {
        fun appDependencies(appDependencies: AppDependencies): Builder

        fun mainFeatureDependencies(mainFeatureDependencies: MainFeatureDependencies): Builder

        fun build(): DFeatureComponent
    }
}
