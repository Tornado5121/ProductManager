package com.zhadko.productsmanager.di

import android.content.Context
import com.google.gson.Gson
import com.zhadko.productsmanager.data.dataSources.database.room.ProductDao
import com.zhadko.productsmanager.data.dataSources.database.room.ProductsDatabase
import com.zhadko.productsmanager.data.dataSources.network.ProductsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ProvideModule {

    @Provides
    fun provideApi(): ProductsApi =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/").build()
            .create(ProductsApi::class.java)

    @Provides
    fun provideDao(context: Context): ProductDao =
        ProductsDatabase.getInstance(context).getDao()

    @Provides
    fun provideGson() = Gson()
}