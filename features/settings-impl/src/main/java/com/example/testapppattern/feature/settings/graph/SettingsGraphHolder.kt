package com.example.testapppattern.feature.settings.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.testapppattern.feature.settings.api.navigation.SettingsRoutes

@Composable
fun rememberSettingsGraphHolder(
    navController: NavHostController,
    currentBackStackEntry: NavBackStackEntry,
): SettingsGraphHolderViewModel {
    val graphEntry = remember(currentBackStackEntry) {
        navController.getBackStackEntry(SettingsRoutes.GRAPH)
    }
    return viewModel(viewModelStoreOwner = graphEntry)
}
