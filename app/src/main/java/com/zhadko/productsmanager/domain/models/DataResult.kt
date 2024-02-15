package com.zhadko.productsmanager.domain.models

import com.zhadko.productsmanager.errors.ProductError

sealed class DataResult {
    data class Success(val list: List<ProductDomain>) : DataResult()
    data class Error(val error: ProductError) : DataResult()
    data object Empty : DataResult()
}