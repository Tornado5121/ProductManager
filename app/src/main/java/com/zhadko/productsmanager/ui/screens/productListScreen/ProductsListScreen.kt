package com.zhadko.productsmanager.ui.screens.productListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhadko.productsmanager.ui.components.AddButton
import com.zhadko.productsmanager.ui.components.ProductsList

@Composable
fun ProductsListScreen(
    viewModelFactory: ProductsListViewModelFactory,
    viewModel: ProductsListViewModel = viewModel(factory = viewModelFactory),
    showInputDialog: () -> Unit,
) {
    val uiState by viewModel.state.collectAsState()

    var isVisibleList by remember { mutableStateOf(true) }
    var isVisibleButton by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(true) }

    when (uiState) {
        ProductsListScreenState.Empty -> {
            isLoading = false
            isVisibleList = false
            Text(text = "The list is empty")
        }

        is ProductsListScreenState.Error -> {
            isLoading = false
            isVisibleButton = false
            Text(text = (uiState as ProductsListScreenState.Error).productError.customerMessage)
        }

        ProductsListScreenState.Idle -> {}

        ProductsListScreenState.Loading -> {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }

        is ProductsListScreenState.Success -> {
            isVisibleButton = true
            isLoading = false
            if (isVisibleList) {
                ProductsList(list = (uiState as ProductsListScreenState.Success).list) {
                    viewModel.deleteProductById(it.id)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AddButton(text = "Show Input Dialog", onClick = showInputDialog)
    }
}