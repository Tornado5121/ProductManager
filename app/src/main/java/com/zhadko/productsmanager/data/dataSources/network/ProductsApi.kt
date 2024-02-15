package com.zhadko.productsmanager.data.dataSources.network

import com.google.gson.JsonObject
import com.zhadko.productsmanager.data.dataSources.network.models.Product
import com.zhadko.productsmanager.data.dataSources.network.models.ProductItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(): Response<Product>

    @POST("products")
    suspend fun addNewProduct(@Body productItem: JsonObject): Response<ProductItem>
}