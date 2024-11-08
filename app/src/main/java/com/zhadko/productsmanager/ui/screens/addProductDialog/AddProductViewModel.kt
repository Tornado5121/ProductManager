package com.zhadko.productsmanager.ui.screens.addProductDialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.productsmanager.domain.models.ProductDomain
import com.zhadko.productsmanager.domain.repositories.ProductsRepository
import kotlinx.coroutines.launch

class AddProductViewModel(
    private val productsRepository: ProductsRepository,
) : ViewModel() {

    fun addNewProduct(product: ProductDomain) {
        viewModelScope.launch {
            productsRepository.addProduct(product)
        }
    }
}