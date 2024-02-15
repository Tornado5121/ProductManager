package com.zhadko.productsmanager.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zhadko.productsmanager.navigation.Routes.PRODUCTS_LIST
import com.zhadko.productsmanager.ui.screens.ProductsListScreen
import com.zhadko.productsmanager.ui.screens.ProductsListViewModelFactory

@Composable
fun AppNavigation(
    viewModelFactory: ProductsListViewModelFactory,
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = PRODUCTS_LIST) {
        composable(
            route = PRODUCTS_LIST,
            enterTransition = { fadeIn() }
        ) {
            ProductsListScreen(viewModelFactory)
        }
    }
}
