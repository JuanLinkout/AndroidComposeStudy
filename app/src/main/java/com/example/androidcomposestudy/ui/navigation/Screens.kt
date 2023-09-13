package com.example.androidcomposestudy.ui.navigation

sealed class Screens(val route: String) {
    object Products : Screens("products_screen")
    object ProductsDetails : Screens("products_details_screen")
}
