package com.example.testapppattern.core.di.feature

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

object FeatureOwnerKeyRegistry {

    private val featureUsageCounters = ConcurrentHashMap<FeatureKey, AtomicLong>()

    fun nextOwnerKey(featureKey: FeatureKey): FeatureInstanceOwnerKey {
        val counter = featureUsageCounters
            .computeIfAbsent(featureKey) { AtomicLong(0L) }
            .incrementAndGet()
        return FeatureInstanceOwnerKey(featureKey = featureKey, instanceId = counter)
    }
}
