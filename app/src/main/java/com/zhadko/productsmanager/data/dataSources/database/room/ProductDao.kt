package com.zhadko.productsmanager.data.dataSources.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("select * from productentity")
    fun getProducts(): List<ProductEntity>

    @Insert
    fun addProductsList(products: List<ProductEntity>)

    @Insert
    fun addNewProduct(product: ProductEntity)
}