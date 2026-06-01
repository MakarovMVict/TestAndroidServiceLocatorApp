package com.example.testapppattern.feature.dfeature.data

import com.example.testapppattern.core.di.scope.FeatureScope
import com.example.testapppattern.feature.dfeature.domain.repository.IDFeatureScreenViewsRepository
import javax.inject.Inject

@FeatureScope
internal class DFeatureScreenViewsRepositoryImpl @Inject constructor() : IDFeatureScreenViewsRepository {
    private var viewsCount: Int = 0

    override suspend fun getViewsCount(): Int = viewsCount

    override suspend fun incrementAndGet(): Int {
        viewsCount += 1
        return viewsCount
    }
}
