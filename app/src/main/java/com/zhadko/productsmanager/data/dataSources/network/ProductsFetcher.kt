package com.zhadko.productsmanager.data.dataSources.network

import com.google.gson.JsonObject
import com.zhadko.productsmanager.data.dataSources.network.models.ProductItem

interface ProductsFetcher {

    suspend fun getProducts(): List<ProductItem>
    suspend fun addNewProduct(body: JsonObject): ProductItem
}