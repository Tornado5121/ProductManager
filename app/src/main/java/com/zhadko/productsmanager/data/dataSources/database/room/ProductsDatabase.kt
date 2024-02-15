package com.zhadko.productsmanager.data.dataSources.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_NAME = "products_database"

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun getDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        fun getInstance(context: Context): ProductsDatabase {
            val currentInstance = INSTANCE
            if (currentInstance != null) {
                return currentInstance
            }

            synchronized(this) {
                val dataBaseInstance = INSTANCE
                return if (dataBaseInstance != null) {
                    dataBaseInstance
                } else {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductsDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}