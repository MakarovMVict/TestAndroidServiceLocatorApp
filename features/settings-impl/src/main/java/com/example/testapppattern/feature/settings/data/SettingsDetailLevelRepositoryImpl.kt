package com.example.testapppattern.feature.settings.data

import com.example.testapppattern.core.di.scope.FeatureScope
import com.example.testapppattern.feature.settings.domain.repository.ISettingsDetailLevelRepository
import javax.inject.Inject

@FeatureScope
internal class SettingsDetailLevelRepositoryImpl @Inject constructor() :
    ISettingsDetailLevelRepository {

    private var detailLevel: String = DEFAULT_DETAIL_LEVEL

    override fun getDetailLevel(): String = detailLevel

    override fun setDetailLevel(level: String) {
        detailLevel = level
    }

    private companion object {
        const val DEFAULT_DETAIL_LEVEL = "Стандарт"
    }
}
