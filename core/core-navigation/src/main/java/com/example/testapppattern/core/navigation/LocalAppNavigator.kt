package com.example.testapppattern.core.navigation

import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppNavigator = staticCompositionLocalOf<AppNavigator> {
    error("AppNavigator is not provided")
}
