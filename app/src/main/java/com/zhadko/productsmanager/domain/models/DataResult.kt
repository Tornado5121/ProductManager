package com.zhadko.productsmanager.domain.models

import com.zhadko.productsmanager.errors.ProductError
import kotlinx.coroutines.flow.Flow

sealed class DataResult {
    data class Success(val list: Flow<List<ProductDomain>>) : DataResult()
    data class Error(val error: ProductError) : DataResult()
    data object Empty : DataResult()
}