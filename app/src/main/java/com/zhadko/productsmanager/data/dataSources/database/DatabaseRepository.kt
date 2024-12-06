package com.zhadko.productsmanager.data.dataSources.database

import com.zhadko.productsmanager.data.dataSources.database.room.ProductEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    suspend fun getProducts(): Flow<List<ProductEntity>>
    suspend fun addNewProduct(productEntity: ProductEntity)
    suspend fun addProducts(productsEntity: List<ProductEntity>)
    suspend fun deleteProductById(productId: Int)
    suspend fun deleteProducts()
}