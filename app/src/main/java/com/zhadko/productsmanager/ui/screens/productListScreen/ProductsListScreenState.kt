package com.zhadko.productsmanager.ui.screens.productListScreen

import com.zhadko.productsmanager.domain.models.ProductDomain
import com.zhadko.productsmanager.errors.ProductError

data class ProductsListScreenState(
    val isLoading: Boolean,
    val list: List<ProductDomain>,
    val productError: ProductError?,
)