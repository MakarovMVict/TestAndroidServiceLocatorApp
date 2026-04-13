package com.example.testapppattern.core.navigation

interface AppNavigator {
    fun navTo(route: String, singleTop: Boolean)
    fun navToClearingTo(route: String, popUpToRoute: String, inclusive: Boolean)
    fun back()
    fun backTo(route: String, inclusive: Boolean = false): Boolean
    fun canGoBack(): Boolean
    fun currentRoute(): String?
    fun replace(route: String)
    fun replaceRoot(route: String)
}
