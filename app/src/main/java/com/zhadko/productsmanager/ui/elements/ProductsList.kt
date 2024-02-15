package com.zhadko.productsmanager.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zhadko.productsmanager.domain.models.ProductDomain

@Composable
fun ProductsList(list: List<ProductDomain>, action: (ProductDomain) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(list) {
            ProductItem(
                title = it.title,
                description = it.description,
                price = it.price,
                category = it.category,
                image = it.image
            ) {
                action(it)
            }
        }
    }
}