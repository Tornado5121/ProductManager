package com.zhadko.productsmanager.ui.screens.productListScreen

import com.zhadko.productsmanager.domain.models.ProductDomain
import com.zhadko.productsmanager.errors.ProductError
import kotlinx.coroutines.flow.Flow

data class ProductsListScreenState(
    val isLoading: Boolean,
    val list: Flow<List<ProductDomain>>,
    val productError: ProductError?,
)