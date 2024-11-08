package com.zhadko.productsmanager.ui.screens.productListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhadko.productsmanager.domain.models.DataResult
import com.zhadko.productsmanager.domain.models.ProductDomain
import com.zhadko.productsmanager.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsListViewModel(
    private val productsRepository: ProductsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(
        ProductsListScreenState(
            isLoading = true,
            list = emptyList(),
            productError = null
        )
    )
    val state = _state.asStateFlow()

    init {
        getProductsList()
    }

    private fun getProductsList() {
        viewModelScope.launch {
            _state.update { _state.value.copy(isLoading = true) }
            when (val result = productsRepository.getProducts()) {
                is DataResult.Success -> _state.update {
                    _state.value.copy(
                        isLoading = false,
                        list = result.list
                    )
                }

                is DataResult.Error -> _state.update {
                    _state.value.copy(
                        isLoading = false,
                        list = emptyList(),
                        productError = null
                    )
                }

                DataResult.Empty -> _state.update {
                    _state.value.copy(
                        isLoading = false,
                        list = emptyList()
                    )
                }
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