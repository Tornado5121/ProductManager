package com.zhadko.productsmanager.domain.iRepositories

import com.zhadko.productsmanager.domain.models.DataResult
import com.zhadko.productsmanager.domain.models.ProductDomain

interface ProductsRepository {

    suspend fun getProducts(): DataResult
    suspend fun addProduct(product: ProductDomain)
    suspend fun deleteProductById(productId: Int)
}