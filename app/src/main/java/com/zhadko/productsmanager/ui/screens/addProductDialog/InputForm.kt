package com.zhadko.productsmanager.ui.screens.addProductDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zhadko.productsmanager.domain.models.ProductDomain
import com.zhadko.productsmanager.ui.components.AddButton

@Composable
fun InputForm(
    viewModelFactory: AddProductViewModelFactory,
    viewModel: AddProductViewModel = viewModel(factory = viewModelFactory),
    goBack: () -> Unit,
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    Column {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("title") }
        )
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("description") }
        )
        TextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("category") }
        )
        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("price") }
        )
        TextField(
            value = image,
            onValueChange = { image = it },
            label = { Text("image") }
        )
        AddButton(text = "Add product") {
            viewModel.addNewProduct(
                ProductDomain(
                    title = title,
                    description = description,
                    category = category,
                    price = price,
                    image = image
                )
            )
            goBack()
        }
    }
}