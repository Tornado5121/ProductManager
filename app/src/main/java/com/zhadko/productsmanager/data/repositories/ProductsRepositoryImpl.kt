package com.zhadko.productsmanager.data.repositories

import com.google.gson.JsonObject
import com.zhadko.productsmanager.data.dataSources.database.DatabaseRepository
import com.zhadko.productsmanager.data.dataSources.database.extensions.asDomain
import com.zhadko.productsmanager.data.dataSources.network.ProductsFetcher
import com.zhadko.productsmanager.data.dataSources.network.extensions.asDatabase
import com.zhadko.productsmanager.domain.iRepositories.ProductsRepository
import com.zhadko.productsmanager.domain.models.DataResult
import com.zhadko.productsmanager.domain.models.ProductDomain
import com.zhadko.productsmanager.errors.ProductError
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val fetcher: ProductsFetcher,
    private val databaseRepository: DatabaseRepository,
) : ProductsRepository {

    override suspend fun getProducts(): DataResult {
        val databaseList = databaseRepository.getProducts()
        databaseList.ifEmpty {
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
        }
        return DataResult.Success(databaseRepository.getProducts().asDomain())
    }

    override suspend fun addProduct(product: ProductDomain) {
        val json = JsonObject()
        json.addProperty("title", product.title)
        json.addProperty("price", product.price)
        json.addProperty("description", product.description)
        json.addProperty("category", product.category)
        json.addProperty("image", product.image)
        val item = fetcher.addNewProduct(json)
        databaseRepository.addNewProduct(item.asDatabase())
    }

    override suspend fun deleteProductById(productId: Int) {
        databaseRepository.deleteProductById(productId)
    }
}