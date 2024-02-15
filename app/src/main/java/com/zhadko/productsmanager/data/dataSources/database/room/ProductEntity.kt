package com.zhadko.productsmanager.data.dataSources.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Int = 0,
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: String,
    val title: String
)
