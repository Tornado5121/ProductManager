package com.zhadko.productsmanager.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.zhadko.productsmanager.domain.models.TextFieldState

@Composable
fun InputForm(
    titleState: TextFieldState = remember { TextFieldState() },
    descriptionState: TextFieldState = remember { TextFieldState() },
    categoryState: TextFieldState = remember { TextFieldState() },
    priceState: TextFieldState = remember { TextFieldState() },
    imageState: TextFieldState = remember { TextFieldState() },
) {
    Column {
        TextField(
            value = titleState.text,
            onValueChange = { titleState.text = it },
            label = { Text("title") }
        )
        TextField(
            value = descriptionState.text,
            onValueChange = { descriptionState.text = it },
            label = { Text("description") }
        )
        TextField(
            value = categoryState.text,
            onValueChange = { categoryState.text = it },
            label = { Text("category") }
        )
        TextField(
            value = priceState.text,
            onValueChange = { priceState.text = it },
            label = { Text("price") }
        )
        TextField(
            value = imageState.text,
            onValueChange = { imageState.text = it },
            label = { Text("image") }
        )
    }
}