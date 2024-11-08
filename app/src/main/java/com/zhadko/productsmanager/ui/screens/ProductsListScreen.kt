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
import androidx.compose.runtime.setValue
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

    var isVisibleInputForm by remember { mutableStateOf(false) }
    var isVisibleList by remember { mutableStateOf(true) }
    var isVisibleButton by remember { mutableStateOf(true) }
    var isFilled by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    var buttonTitle by remember { mutableStateOf("Show Input Dialog") }

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
        if (isVisibleInputForm) {
            InputForm(
                titleState = title,
                descriptionState = description,
                categoryState = category,
                priceState = price,
                imageState = image
            )
        }
        if (isVisibleButton) {
            AddButton(text = buttonTitle, onClick = {
                buttonTitle = "Add product"
                isVisibleInputForm = true
                isVisibleList = false

                isFilled =
                    title.text.isNotBlank() &&
                            description.text.isNotBlank() &&
                            category.text.isNotBlank() &&
                            price.text.isNotBlank() &&
                            image.text.isNotBlank()

                if (isFilled) {
                    buttonTitle = "Show Input Dialog"
                    isVisibleInputForm = false
                    isVisibleList = true
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