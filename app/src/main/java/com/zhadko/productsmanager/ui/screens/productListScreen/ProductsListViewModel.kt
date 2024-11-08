package com.zhadko.productsmanager.ui.screens.productListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.productsmanager.domain.repositories.ProductsRepository
import com.zhadko.productsmanager.domain.models.DataResult
import com.zhadko.productsmanager.domain.models.ProductDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsListViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ProductsListScreenState>(ProductsListScreenState.Idle)
    val state = _state.asStateFlow()

    init {
        getProductsList()
    }

    private fun getProductsList() {
        viewModelScope.launch {
            _state.emit(ProductsListScreenState.Loading)
            when (val result = productsRepository.getProducts()) {
                is DataResult.Error -> _state.update { ProductsListScreenState.Error(result.error) }
                is DataResult.Success -> _state.update { ProductsListScreenState.Success(result.list) }
                DataResult.Empty -> _state.update { ProductsListScreenState.Empty }
            }
        }
    }

    fun addNewProduct(product: ProductDomain) {
        viewModelScope.launch {
            productsRepository.addProduct(product)
            getProductsList()
        }
    }

    fun deleteProductById(productId: Int) {
        viewModelScope.launch {
            productsRepository.deleteProductById(productId)
            getProductsList()
        }
    }
}