package com.zhadko.productsmanager.data.dataSources.database

import com.zhadko.productsmanager.data.dataSources.database.room.ProductDao
import com.zhadko.productsmanager.data.dataSources.database.room.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val dao: ProductDao
) : DatabaseRepository {

    override suspend fun getProducts(): List<ProductEntity> {
        return withContext(Dispatchers.IO) { dao.getProducts() }
    }

    override suspend fun addNewProduct(productEntity: ProductEntity) {
        withContext(Dispatchers.IO) { dao.addNewProduct(productEntity) }
    }

    override suspend fun addProducts(productsEntity: List<ProductEntity>) {
        withContext(Dispatchers.IO) { dao.addProductsList(productsEntity) }
    }
}