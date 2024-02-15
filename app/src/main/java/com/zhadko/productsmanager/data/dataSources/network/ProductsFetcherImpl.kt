package com.zhadko.productsmanager.data.dataSources.network

import com.google.gson.JsonObject
import com.zhadko.productsmanager.data.dataSources.network.models.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsFetcherImpl @Inject constructor(
    private val api: ProductsApi
) : ProductsFetcher {

    override suspend fun getProducts(): List<ProductItem> =
        withContext(Dispatchers.IO) { api.getProducts().body() ?: emptyList() }

    override suspend fun addNewProduct(body: JsonObject): ProductItem {
        return withContext(Dispatchers.IO) {
            val result = api.addNewProduct(body)
            result.body() ?: throw Exception()
        }
    }
}