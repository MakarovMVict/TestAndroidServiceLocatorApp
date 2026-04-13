package com.example.testapppattern.core.di.feature

interface IFeatureComponentFactory<T> {

    /** Ключи фич, от которых зависит эта фича (для получения из [FeatureFactoriesLocator]). */
    val dependenciesKeysList: List<String>

    fun getComponent(): T

    /** Удаление/освобождение компонента фичи; [featureKey] — ключ удаляемой фичи. */
    fun removeComponent(featureKey: String)
}
