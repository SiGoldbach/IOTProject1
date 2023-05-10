package com.example.iotproject1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.iotproject1.screens.ScreenWithScaffold
import com.example.iotproject1.viewmodels.OnAppViewModel

@Composable
fun NavigationAppHost(navHostController: NavHostController) {
    var dependableStartDestination: String = Destination.MainScreen.route
    val onAppViewModel = OnAppViewModel()


    NavHost(navController = navHostController, startDestination = dependableStartDestination) {
        composable(Destination.MainScreen.route) {
            ScreenWithScaffold(
                navHostController,
                onAppViewModel = onAppViewModel
            )
        }

    }


}