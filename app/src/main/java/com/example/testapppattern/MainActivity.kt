package com.example.testapppattern

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testapppattern.app.navigation.AppNavigation
import com.example.testapppattern.app.navigation.AppNavigationStrings
import com.example.testapppattern.app.navigation.createAppNavigator
import com.example.testapppattern.core.navigation.AppFeatureNavGraph
import com.example.testapppattern.core.navigation.LocalAppNavigator
import com.example.testapppattern.core.ui.theme.TestAppPatternTheme
import com.example.testapppattern.feature.main.api.navigation.MainRoutes
import com.example.testapppattern.feature.payments.api.navigation.PaymentsRoutes
import com.example.testapppattern.feature.settings.api.navigation.SettingsRoutes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val app = application as TestAppPatternApplication
        setContent {
            TestAppPatternTheme {
                AppRoot(featureNavGraphs = app.featureNavGraphs)
            }
        }
    }
}

private data class TabItem(
    val graphRoute: String,
    val icon: ImageVector,
    val label: String,
)

@Composable
private fun AppRoot(featureNavGraphs: List<AppFeatureNavGraph>) {
    val navController = rememberNavController()
    val navigator = remember(navController) { createAppNavigator(navController) }
    val tabItems = listOf(
        TabItem(MainRoutes.GRAPH, Icons.Filled.Home, AppNavigationStrings.NAV_HOME),
        TabItem(PaymentsRoutes.GRAPH, Icons.Filled.Payment, AppNavigationStrings.NAV_PAYMENTS),
        TabItem(SettingsRoutes.GRAPH, Icons.Filled.Settings, AppNavigationStrings.NAV_SETTINGS),
    )
    CompositionLocalProvider(LocalAppNavigator provides navigator) {
        Scaffold(
            bottomBar = {
                MainTabBar(tabItems = tabItems, navController = navController)
            },
        ) { innerPadding ->
            AppNavigation(
                navController = navController,
                featureNavGraphs = featureNavGraphs,
                startDestination = MainRoutes.GRAPH,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Composable
private fun MainTabBar(
    tabItems: List<TabItem>,
    navController: NavHostController,
) {
    val navigator = LocalAppNavigator.current
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        tabItems.forEach { tab ->
            NavigationBarItem(
                icon = { Icon(tab.icon, contentDescription = null) },
                label = { Text(tab.label) },
                selected = currentDestination?.hierarchy?.any { it.route == tab.graphRoute } == true,
                onClick = { navigator.replaceRoot(tab.graphRoute) },
            )
        }
    }
}
