package com.zhadko.productsmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zhadko.productsmanager.di.DaggerAppComponent
import com.zhadko.productsmanager.navigation.AppNavigation
import com.zhadko.productsmanager.ui.screens.addProductDialog.AddProductViewModelFactory
import com.zhadko.productsmanager.ui.screens.productListScreen.ProductsListViewModelFactory
import com.zhadko.productsmanager.ui.theme.ProductsManagerTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private val appComponent = DaggerAppComponent.builder().context(this).build()

    @Inject
    lateinit var productsListViewModelFactory: ProductsListViewModelFactory

    @Inject
    lateinit var addProductViewModelFactory: AddProductViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContent {
            ProductsManagerTheme {
                AppNavigation(productsListViewModelFactory, addProductViewModelFactory)
            }
        }
    }
}