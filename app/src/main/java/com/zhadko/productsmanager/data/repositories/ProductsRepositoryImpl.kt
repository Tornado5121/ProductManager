package com.zhadko.productsmanager.data.repositories

import com.google.gson.JsonObject
import com.zhadko.productsmanager.data.dataSources.database.DatabaseRepository
import com.zhadko.productsmanager.data.dataSources.database.extensions.asDomain
import com.zhadko.productsmanager.data.dataSources.network.ProductsFetcher
import com.zhadko.productsmanager.data.dataSources.network.extensions.asDatabase
import com.zhadko.productsmanager.domain.extensions.asDatabase
import com.zhadko.productsmanager.domain.models.DataResult
import com.zhadko.productsmanager.domain.models.ProductDomain
import com.zhadko.productsmanager.domain.repositories.ProductsRepository
import com.zhadko.productsmanager.errors.ProductError
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val fetcher: ProductsFetcher,
    private val databaseRepository: DatabaseRepository,
) : ProductsRepository {

    override suspend fun getProducts(): DataResult {

        val productsList = try {
            fetcher.getProducts()
        } catch (e: Exception) {
            return DataResult.Error(ProductError(techMessage = e.message.toString()))
        }
        if (productsList.isEmpty()) {
            return DataResult.Empty
        } else {
            databaseRepository.addProducts(productsList.asDatabase())
        }

        return DataResult.Success(databaseRepository.getProducts().map { it.asDomain() })
    }

    override suspend fun addProduct(product: ProductDomain) {
        databaseRepository.addNewProduct(product.asDatabase())

        val json = JsonObject()
        json.addProperty("title", product.title)
        json.addProperty("price", product.price)
        json.addProperty("description", product.description)
        json.addProperty("category", product.category)
        json.addProperty("image", product.image)
        fetcher.addNewProduct(json)
    }

    override suspend fun deleteProductById(productId: Int) {
        databaseRepository.deleteProductById(productId)
    }
}