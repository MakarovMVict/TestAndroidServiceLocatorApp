package com.example.testapppattern.feature.settings.domain.repository

internal interface ISettingsDetailLevelRepository {
    fun getDetailLevel(): String
    fun setDetailLevel(level: String)
}
