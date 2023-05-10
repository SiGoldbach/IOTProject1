package com.example.iotproject1.navigation

sealed class Destination(val route: String) {
    object MainScreen: Destination("Main")
}