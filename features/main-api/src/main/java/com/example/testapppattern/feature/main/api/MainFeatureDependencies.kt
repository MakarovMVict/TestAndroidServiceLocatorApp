package com.example.testapppattern.feature.main.api

import com.example.testapppattern.feature.main.domain.usecase.GetMainScreenViewsCountUseCase
import com.example.testapppattern.feature.main.domain.usecase.RecordMainScreenViewUseCase
import com.example.testapppattern.feature.main.domain.usecase.ResetMainScreenViewsCountUseCase

/**
 * Публичная поверхность зависимостей фичи main для других фич (только через main-api).
 * Реализуется Dagger-компонентом в main-impl; дочерние фичи подключают через
 * `@Component(dependencies = [MainFeatureDependencies::class])`.
 */
interface MainFeatureDependencies {
    fun getMainScreenViewsCountUseCase(): GetMainScreenViewsCountUseCase
    fun recordMainScreenViewUseCase(): RecordMainScreenViewUseCase
    fun resetMainScreenViewsCountUseCase(): ResetMainScreenViewsCountUseCase
}
