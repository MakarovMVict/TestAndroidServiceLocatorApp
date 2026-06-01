package com.example.testapppattern.feature.dfeature.domain.repository

interface IDFeatureScreenViewsRepository {
    suspend fun getViewsCount(): Int
    suspend fun incrementAndGet(): Int
}
