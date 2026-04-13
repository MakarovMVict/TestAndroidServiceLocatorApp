package com.example.testapppattern.core.di.feature

interface FeatureFactoriesLocator {
    fun getFactory(key: String): IFeatureComponentFactory<*>
    fun removeFactory(key: String)
}
