package com.zhadko.productsmanager.domain.models

data class ProductDomain(
    val id: Int = 1,
    val category: String,
    val description: String,
    val image: String = "",
    val price: String,
    val title: String
)
