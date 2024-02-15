package com.zhadko.productsmanager.domain.extensions

import com.zhadko.productsmanager.data.dataSources.database.room.ProductEntity
import com.zhadko.productsmanager.data.dataSources.network.models.ProductItem
import com.zhadko.productsmanager.domain.models.ProductDomain

fun ProductDomain.asDatabase(): ProductEntity {
    return ProductEntity(
        id = id,
        category = category,
        description = description,
        image = image,
        price = price,
        title = title
    )
}

fun List<ProductDomain>.asDatabase(): List<ProductEntity> {
    return map {
        it.asDatabase()
    }
}

fun ProductDomain.asNetwork(): ProductItem {
    return ProductItem(
        id = id,
        category = category,
        description = description,
        image = image,
        price = price,
        title = title
    )
}

fun ProductDomain.asPostNetwork(): ProductItemPost {
    return ProductItemPost(
        category = category,
        description = description,
        image = image,
        price = price,
        title = title
    )
}

data class ProductItemPost(
    val category: String,
    val description: String,
    val image: String,
    val price: String,
    val title: String
)

fun List<ProductDomain>.asNetwork(): List<ProductItem> {
    return map {
        it.asNetwork()
    }
}