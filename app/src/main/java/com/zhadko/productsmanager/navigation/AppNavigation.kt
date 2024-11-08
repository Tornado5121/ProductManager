package com.zhadko.productsmanager.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.zhadko.productsmanager.navigation.Routes.INPUT_DIALOG
import com.zhadko.productsmanager.navigation.Routes.PRODUCTS_LIST
import com.zhadko.productsmanager.ui.screens.addProductDialog.AddProductViewModelFactory
import com.zhadko.productsmanager.ui.screens.addProductDialog.InputForm
import com.zhadko.productsmanager.ui.screens.productListScreen.ProductsListScreen
import com.zhadko.productsmanager.ui.screens.productListScreen.ProductsListViewModelFactory

@Composable
fun AppNavigation(
    productListViewModelFactory: ProductsListViewModelFactory,
    addProductViewModelFactory: AddProductViewModelFactory,
    navController: NavHostController = rememberNavController(),
) {

    NavHost(navController = navController, startDestination = PRODUCTS_LIST) {
        composable(
            route = PRODUCTS_LIST,
            enterTransition = { fadeIn() }
        ) {
            ProductsListScreen(productListViewModelFactory,
                showInputDialog = { navController.navigate(INPUT_DIALOG) })
        }

        dialog(INPUT_DIALOG) {
            InputForm(addProductViewModelFactory) {
                navController.popBackStack()
            }
        }
    }
}
