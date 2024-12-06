package com.zhadko.productsmanager.data.dataSources.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("select * from productentity")
    fun getProducts(): Flow<List<ProductEntity>>

    @Insert
    fun addProductsList(products: List<ProductEntity>)

    @Insert
    fun addNewProduct(product: ProductEntity)

    @Query("DELETE FROM productentity WHERE id = :productId")
    fun deleteProductById(productId: Int)

    @Query("DELETE FROM productentity")
    fun deleteProducts()
}