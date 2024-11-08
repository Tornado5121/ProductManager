package com.zhadko.productsmanager.ui.screens.productListScreen

import com.zhadko.productsmanager.domain.models.ProductDomain
import com.zhadko.productsmanager.errors.ProductError

sealed class ProductsListScreenState {
    data object Idle : ProductsListScreenState()
    data object Loading : ProductsListScreenState()
    data object Empty : ProductsListScreenState()
    data class Success(val list: List<ProductDomain>) : ProductsListScreenState()
    data class Error(val productError: ProductError) : ProductsListScreenState()
}