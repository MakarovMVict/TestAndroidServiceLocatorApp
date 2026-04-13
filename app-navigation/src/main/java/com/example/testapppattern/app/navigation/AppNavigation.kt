package com.example.testapppattern.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.testapppattern.core.navigation.AppFeatureNavGraph

@Composable
fun AppNavigation(
    navController: NavHostController,
    featureNavGraphs: List<AppFeatureNavGraph>,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        featureNavGraphs.forEach { graph ->
            graph.register(this, navController)
        }
    }
}
