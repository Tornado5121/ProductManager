package com.zhadko.productsmanager.data.dataSources.database

import com.zhadko.productsmanager.data.dataSources.database.room.ProductEntity

interface DatabaseRepository {

    suspend fun getProducts(): List<ProductEntity>
    suspend fun addNewProduct(productEntity: ProductEntity)
    suspend fun addProducts(productsEntity: List<ProductEntity>)
}