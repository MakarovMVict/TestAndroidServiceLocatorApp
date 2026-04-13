package com.example.testapppattern.feature.payments.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.testapppattern.feature.payments.api.navigation.PaymentsRoutes

@Composable
fun rememberPaymentsGraphHolder(
    navController: NavHostController,
    currentBackStackEntry: NavBackStackEntry,
): PaymentsGraphHolderViewModel {
    val graphEntry = remember(currentBackStackEntry) {
        navController.getBackStackEntry(PaymentsRoutes.GRAPH)
    }
    return viewModel(viewModelStoreOwner = graphEntry)
}
