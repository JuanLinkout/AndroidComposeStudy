package com.example.androidcomposestudy.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidcomposestudy.ui.screens.productDetails.ProductDetailsScreen
import com.example.androidcomposestudy.ui.screens.products.ProductsScreen
import com.example.androidcomposestudy.utils.generateProductList


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Products.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screens.Products.route) {
            ProductsScreen(navController = navController)
        }

        composable(Screens.ProductsDetails.route) {
            val product by remember { mutableStateOf(generateProductList(1)[0]) }
            ProductDetailsScreen(navController = navController, product)
        }
    }
}
