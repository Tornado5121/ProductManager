package com.zhadko.productsmanager.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhadko.productsmanager.domain.iRepositories.ProductsRepository
import javax.inject.Inject

class ProductsListViewModelFactory @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsListViewModel(productsRepository) as T
    }
}