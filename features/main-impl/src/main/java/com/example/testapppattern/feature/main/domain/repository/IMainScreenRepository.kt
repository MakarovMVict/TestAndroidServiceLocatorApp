package com.example.testapppattern.feature.main.domain.repository

interface IMainScreenRepository {
    suspend fun getViewsCount(): Int
    suspend fun setViewsCount(count: Int)
    suspend fun resetViewsCount()
}

