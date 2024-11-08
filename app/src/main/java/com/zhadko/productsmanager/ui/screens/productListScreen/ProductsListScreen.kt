package com.zhadko.productsmanager.ui.screens.productListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    if (uiState.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AddButton(text = "Show Input Dialog", onClick = showInputDialog)

            ProductsList(list = uiState.list) {
                viewModel.deleteProductById(it.id)
            }
        }
    }
}