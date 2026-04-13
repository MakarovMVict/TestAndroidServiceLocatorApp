package com.example.testapppattern.feature.main.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.testapppattern.feature.main.api.navigation.MainRoutes

@Composable
fun rememberMainGraphHolder(
    navController: NavHostController,
    currentBackStackEntry: NavBackStackEntry,
): MainGraphHolderViewModel {
    val graphEntry = remember(currentBackStackEntry) {
        navController.getBackStackEntry(MainRoutes.GRAPH)
    }
    return viewModel(viewModelStoreOwner = graphEntry)
}
