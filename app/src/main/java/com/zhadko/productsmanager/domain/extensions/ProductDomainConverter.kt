package com.zhadko.productsmanager.domain.extensions

import com.zhadko.productsmanager.data.dataSources.database.room.ProductEntity
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