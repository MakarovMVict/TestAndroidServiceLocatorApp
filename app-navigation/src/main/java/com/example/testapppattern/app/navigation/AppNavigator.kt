package com.example.testapppattern.app.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.testapppattern.core.navigation.AppNavigator

fun createAppNavigator(navController: NavHostController): AppNavigator =
    ComposeAppNavigator(navController)

internal class ComposeAppNavigator(
    private val navController: NavHostController,
) : AppNavigator {

    override fun navTo(route: String, singleTop: Boolean) {
        navController.navigate(route) {
            launchSingleTop = singleTop
        }
    }

    override fun navToClearingTo(route: String, popUpToRoute: String, inclusive: Boolean) {
        navController.navigate(route) {
            popUpTo(popUpToRoute) { this.inclusive = inclusive }
            launchSingleTop = true
        }
    }

    override fun back() {
        navController.popBackStack()
    }

    override fun backTo(route: String, inclusive: Boolean): Boolean {
        return navController.popBackStack(route, inclusive)
    }

    override fun canGoBack(): Boolean {
        return navController.previousBackStackEntry != null
    }

    override fun currentRoute(): String? {
        return navController.currentDestination?.route
    }

    override fun replace(route: String) {
        navController.navigate(route) {
            popUpTo(navController.currentDestination?.route ?: return@navigate) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    override fun replaceRoot(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}
