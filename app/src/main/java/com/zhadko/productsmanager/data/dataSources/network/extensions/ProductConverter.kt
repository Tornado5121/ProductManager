package com.zhadko.productsmanager.data.dataSources.network.extensions

import com.zhadko.productsmanager.data.dataSources.database.room.ProductEntity
import com.zhadko.productsmanager.data.dataSources.network.models.ProductItem

fun ProductItem.asDatabase(): ProductEntity {
    return ProductEntity(
        id = id,
        category = category,
        description = description,
        title = title,
        price = price,
        image = image
    )
}

fun List<ProductItem>.asDatabase(): List<ProductEntity> {
    return map {
        it.asDatabase()
    }
}