package com.example.testapppattern.feature.dfeature.di

import com.example.testapppattern.core.di.scope.FeatureScope
import com.example.testapppattern.feature.dfeature.DFeatureViewModel
import com.example.testapppattern.feature.dfeature.api.DFeatureDependencies
import dagger.Component

@FeatureScope
@Component(
    modules = [
        DFeatureBindingsModule::class,
    ],
)
interface DFeatureComponent : DFeatureDependencies {

    fun dFeatureViewModel(): DFeatureViewModel
}
