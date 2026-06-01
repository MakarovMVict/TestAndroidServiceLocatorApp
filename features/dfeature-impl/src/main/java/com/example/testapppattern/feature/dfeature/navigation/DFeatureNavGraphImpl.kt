package com.example.testapppattern.feature.dfeature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.testapppattern.feature.dfeature.DFeatureScreen
import com.example.testapppattern.feature.dfeature.api.navigation.DFeatureNavGraph
import com.example.testapppattern.feature.dfeature.api.navigation.DFeatureRoutes
import com.example.testapppattern.feature.dfeature.graph.DFeatureGraphHolderViewModel
import com.example.testapppattern.feature.dfeature.graph.rememberDFeatureGraphHolder

class DFeatureNavGraphImpl : DFeatureNavGraph {
    override fun register(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.navigation(
            startDestination = DFeatureRoutes.ROOT,
            route = DFeatureRoutes.GRAPH,
        ) {
            composable(DFeatureRoutes.ROOT) { backStackEntry ->
                DFeatureGraphHolderScope(navController, backStackEntry) { holder ->
                    DFeatureScreen(graphHolder = holder)
                }
            }
        }
    }
}

@Composable
private fun DFeatureGraphHolderScope(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry,
    content: @Composable (DFeatureGraphHolderViewModel) -> Unit,
) {
    val holder = rememberDFeatureGraphHolder(navController, backStackEntry)
    content(holder)
}
