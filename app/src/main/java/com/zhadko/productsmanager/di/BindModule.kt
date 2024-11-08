package com.zhadko.productsmanager.di

import com.zhadko.productsmanager.data.dataSources.database.DatabaseRepository
import com.zhadko.productsmanager.data.dataSources.database.DatabaseRepositoryImpl
import com.zhadko.productsmanager.data.dataSources.network.ProductsFetcher
import com.zhadko.productsmanager.data.dataSources.network.ProductsFetcherImpl
import com.zhadko.productsmanager.data.repositories.ProductsRepositoryImpl
import com.zhadko.productsmanager.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BindModule {

    @Binds
    abstract fun bindFetcher(productsFetcher: ProductsFetcherImpl): ProductsFetcher

    @Binds
    abstract fun bindProductsRepository(productsRepository: ProductsRepositoryImpl): ProductsRepository

    @Binds
    abstract fun bindDatabaseRepository(databaseRepository: DatabaseRepositoryImpl): DatabaseRepository
}