package com.zhadko.productsmanager.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhadko.productsmanager.domain.models.ProductDomain
import com.zhadko.productsmanager.domain.models.TextFieldState
import com.zhadko.productsmanager.ui.elements.AddButton
import com.zhadko.productsmanager.ui.elements.InputForm
import com.zhadko.productsmanager.ui.elements.ProductsList

@Composable
fun ProductsListScreen(
    viewModelFactory: ProductsListViewModelFactory,
    viewModel: ProductsListViewModel = viewModel(factory = viewModelFactory)
) {
    val uiState by viewModel.state.collectAsState()

    val title = remember { TextFieldState() }
    val description = remember { TextFieldState() }
    val category = remember { TextFieldState() }
    val price = remember { TextFieldState() }
    val image = remember { TextFieldState() }

    val isVisibleInputForm = remember { mutableStateOf(false) }
    val isVisibleList = remember { mutableStateOf(true) }
    val isVisibleButton = remember { mutableStateOf(true) }
    val isFilled = remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(true) }
    val buttonTitle = remember { mutableStateOf("Show Input Dialog") }

    when (uiState) {
        ProductsListScreenState.Empty -> {
            isLoading.value = false
            isVisibleList.value = false
            Text(text = "The list is empty")
        }

        is ProductsListScreenState.Error -> {
            isLoading.value = false
            isVisibleButton.value = false
            Text(text = (uiState as ProductsListScreenState.Error).productError.customerMessage)
        }

        ProductsListScreenState.Idle -> {}

        ProductsListScreenState.Loading -> {
            if (isLoading.value) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }

        is ProductsListScreenState.Success -> {
            isVisibleButton.value = true
            isLoading.value = false
            if (isVisibleList.value) {
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
        if (isVisibleInputForm.value) {
            InputForm(
                titleState = title,
                descriptionState = description,
                categoryState = category,
                priceState = price,
                imageState = image
            )
        }
        if (isVisibleButton.value) {
            AddButton(text = buttonTitle.value, onClick = {
                buttonTitle.value = "Add product"
                isVisibleInputForm.value = true
                isVisibleList.value = false

                isFilled.value =
                    title.text.isNotBlank() &&
                            description.text.isNotBlank() &&
                            category.text.isNotBlank() &&
                            price.text.isNotBlank() &&
                            image.text.isNotBlank()

                if (isFilled.value) {
                    buttonTitle.value = "Show Input Dialog"
                    isVisibleInputForm.value = false
                    isVisibleList.value = true
                    viewModel.addNewProduct(
                        ProductDomain(
                            title = title.text,
                            price = price.text,
                            description = description.text,
                            image = image.text,
                            category = category.text,
                        )
                    )
                }
                title.text = ""
                description.text = ""
                category.text = ""
                price.text = ""
                image.text = ""
            })
        }
    }
}