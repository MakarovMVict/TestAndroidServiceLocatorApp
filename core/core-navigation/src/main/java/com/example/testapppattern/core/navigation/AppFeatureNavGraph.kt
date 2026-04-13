package com.example.testapppattern.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun interface AppFeatureNavGraph {
    fun register(navGraphBuilder: NavGraphBuilder, navController: NavHostController)
}
