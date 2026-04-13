package com.example.testapppattern.core.di.feature

import java.util.concurrent.ConcurrentHashMap

object FeatureServiceLocator : FeatureFactoriesLocator {

    private val factories = ConcurrentHashMap<String, IFeatureComponentFactory<*>>()
    private val factoryCreators = ConcurrentHashMap<String, () -> IFeatureComponentFactory<*>>()

    /**
     * Регистрирует способ создания фабрики по ключу. Вызывается при старте приложения (например из [android.app.Application]).
     * Сама фабрика создаётся при первом [getFactory] для этого ключа.
     */
    fun registerFactoryCreator(key: String, creator: () -> IFeatureComponentFactory<*>) {
        factoryCreators[key] = creator
    }

    override fun getFactory(key: String): IFeatureComponentFactory<*> {
        return factories.computeIfAbsent(key) {
            val creator = factoryCreators[key]
                ?: error("No factory creator registered for key: $key")
            creator()
        }
    }

    override fun removeFactory(key: String) {
        factories.get(key)?.removeComponent(key)
    }
}
