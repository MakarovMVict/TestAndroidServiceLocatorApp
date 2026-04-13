package com.example.testapppattern.feature.main.data

import android.content.Context
import androidx.core.content.edit
import com.example.testapppattern.feature.main.domain.repository.IMainScreenRepository
import com.example.testapppattern.core.di.ApplicationContext
import com.example.testapppattern.core.di.scope.FeatureScope
import javax.inject.Inject

@FeatureScope
internal class MainScreenViewsRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
) : IMainScreenRepository {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override suspend fun getViewsCount(): Int = prefs.getInt(KEY_VIEWS_COUNT, 0)

    override suspend fun setViewsCount(count: Int) {
        prefs.edit { putInt(KEY_VIEWS_COUNT, count) }
    }

    override suspend fun resetViewsCount() {
        prefs.edit { putInt(KEY_VIEWS_COUNT, 0) }
    }

    private companion object {
        private const val PREFS_NAME = "main_screen_views"
        private const val KEY_VIEWS_COUNT = "views_count"
    }
}

