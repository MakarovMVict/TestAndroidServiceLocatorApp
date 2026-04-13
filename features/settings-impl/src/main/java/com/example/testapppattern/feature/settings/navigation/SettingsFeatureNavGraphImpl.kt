package com.example.testapppattern.feature.settings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.testapppattern.feature.settings.ParametersScreen
import com.example.testapppattern.feature.settings.SettingsScreen
import com.example.testapppattern.feature.settings.api.navigation.SettingsFeatureNavGraph
import com.example.testapppattern.feature.settings.api.navigation.SettingsRoutes
import com.example.testapppattern.feature.settings.graph.SettingsGraphHolderViewModel
import com.example.testapppattern.feature.settings.graph.rememberSettingsGraphHolder

class SettingsFeatureNavGraphImpl : SettingsFeatureNavGraph {
    override fun register(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.navigation(
            startDestination = SettingsRoutes.SETTINGS,
            route = SettingsRoutes.GRAPH,
        ) {
            composable(SettingsRoutes.SETTINGS) { backStackEntry ->
                SettingsGraphHolderScope(navController, backStackEntry) { holder ->
                    SettingsScreen(graphHolder = holder)
                }
            }
            composable(SettingsRoutes.PARAMETERS) { backStackEntry ->
                SettingsGraphHolderScope(navController, backStackEntry) { holder ->
                    ParametersScreen(graphHolder = holder)
                }
            }
        }
    }
}

@Composable
private fun SettingsGraphHolderScope(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry,
    content: @Composable (SettingsGraphHolderViewModel) -> Unit,
) {
    val holder = rememberSettingsGraphHolder(navController, backStackEntry)
    content(holder)
}
