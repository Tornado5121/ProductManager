package com.zhadko.productsmanager.ui.screens.addProductDialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhadko.productsmanager.domain.repositories.ProductsRepository
import javax.inject.Inject

class AddProductViewModelFactory @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddProductViewModel(productsRepository) as T
    }
}