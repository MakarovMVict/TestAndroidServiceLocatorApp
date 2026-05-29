package com.example.testapppattern.core.di.feature

import java.util.concurrent.ConcurrentHashMap

object FeatureServiceLocator : FeatureFactoriesLocator {

    private val factories = ConcurrentHashMap<FeatureKey, IFeatureComponentFactory<*>>()
    private val factoryCreators = ConcurrentHashMap<FeatureKey, () -> IFeatureComponentFactory<*>>()

    /**
     * Регистрирует способ создания фабрики по ключу. Вызывается при старте приложения (например из [android.app.Application]).
     * Сама фабрика создаётся при первом [getFactory] для этого ключа.
     */
    fun registerFactoryCreator(key: FeatureKey, creator: () -> IFeatureComponentFactory<*>) {
        val previous = factoryCreators.putIfAbsent(key, creator)
        require(previous == null) {
            "Factory creator already registered for key: $key"
        }
    }

    override fun getFactory(key: FeatureKey): IFeatureComponentFactory<*> {
        return factories.computeIfAbsent(key) {
            val creator = factoryCreators[key]
                ?: error("No factory creator registered for key: $key")
            creator()
        }
    }

    override fun removeFactory(key: FeatureKey, ownerKey: FeatureInstanceOwnerKey, hardRemove: Boolean) {
        val factory = factories[key] ?: run {
            return
        }
        factory.removeComponent(featureKey = key, ownerKey = ownerKey, hardRemove = hardRemove)
    }
}
