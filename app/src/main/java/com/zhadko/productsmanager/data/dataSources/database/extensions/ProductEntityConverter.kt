package com.zhadko.productsmanager.data.dataSources.database.extensions

import com.zhadko.productsmanager.data.dataSources.database.room.ProductEntity
import com.zhadko.productsmanager.domain.models.ProductDomain

fun ProductEntity.asDomain(): ProductDomain {
    return ProductDomain(
        id = id,
        category = category,
        description = description,
        image = image,
        price = price,
        title = title
    )
}

fun List<ProductEntity>.asDomain(): List<ProductDomain> {
    return map {
        it.asDomain()
    }
}