package com.example.testapppattern.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.testapppattern.feature.main.HomeScreen
import com.example.testapppattern.feature.main.api.navigation.MainFeatureNavGraph
import com.example.testapppattern.feature.main.api.navigation.MainRoutes
import com.example.testapppattern.feature.main.graph.rememberMainGraphHolder

class MainFeatureNavGraphImpl : MainFeatureNavGraph {
    override fun register(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.navigation(
            startDestination = MainRoutes.HOME,
            route = MainRoutes.GRAPH,
        ) {
            composable(MainRoutes.HOME) { backStackEntry ->
                val holder = rememberMainGraphHolder(navController, backStackEntry)
                HomeScreen(graphHolder = holder)
            }
        }
    }
}
