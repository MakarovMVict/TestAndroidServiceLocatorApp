package com.example.testapppattern.core.di.feature

interface IFeatureComponentFactory<T> {

    /** Ключи фич, от которых зависит эта фича (для получения из [FeatureFactoriesLocator]). */
    val dependenciesKeysList: Set<FeatureKey>

    fun getComponent(ownerKey: FeatureInstanceOwnerKey): T

    /** Удаление/освобождение компонента фичи; [featureKey] — ключ удаляемой фичи. */
    fun removeComponent(featureKey: FeatureKey, ownerKey: FeatureInstanceOwnerKey, hardRemove: Boolean)
}
