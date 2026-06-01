package com.example.testapppattern.feature.dfeature.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.testapppattern.feature.dfeature.api.navigation.DFeatureRoutes

@Composable
fun rememberDFeatureGraphHolder(
    navController: NavHostController,
    currentBackStackEntry: NavBackStackEntry,
): DFeatureGraphHolderViewModel {
    val graphEntry = remember(currentBackStackEntry) {
        navController.getBackStackEntry(DFeatureRoutes.GRAPH)
    }
    return viewModel(viewModelStoreOwner = graphEntry)
}
