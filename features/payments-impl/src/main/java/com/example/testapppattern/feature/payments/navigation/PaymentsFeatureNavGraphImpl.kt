package com.example.testapppattern.feature.payments.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.testapppattern.feature.payments.PaymentsScreen
import com.example.testapppattern.feature.payments.api.navigation.PaymentsFeatureNavGraph
import com.example.testapppattern.feature.payments.api.navigation.PaymentsRoutes
import com.example.testapppattern.feature.payments.graph.rememberPaymentsGraphHolder

class PaymentsFeatureNavGraphImpl : PaymentsFeatureNavGraph {
    override fun register(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.navigation(
            startDestination = PaymentsRoutes.ROOT,
            route = PaymentsRoutes.GRAPH,
        ) {
            composable(PaymentsRoutes.ROOT) { backStackEntry ->
                rememberPaymentsGraphHolder(navController, backStackEntry)
                val graphEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(PaymentsRoutes.GRAPH)
                }
                PaymentsScreen(graphEntry = graphEntry)
            }
        }
    }
}
