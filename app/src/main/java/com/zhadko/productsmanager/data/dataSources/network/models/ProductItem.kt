package com.zhadko.productsmanager.data.dataSources.network.models

import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("title")
    val title: String
)