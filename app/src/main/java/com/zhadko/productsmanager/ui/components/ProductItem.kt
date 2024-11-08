package com.zhadko.productsmanager.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProductItem(
    title: String,
    description: String,
    price: String,
    category: String,
    image: String,
    action: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 20.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .border(2.dp, Color.Black)
            .pointerInput(Unit) {
                detectTapGestures {
                    action()
                }
            }

    ) {
        AsyncImage(model = image, contentDescription = "ddfs")
        Text(text = title)
        Text(text = description)
        Text(text = price)
        Text(text = category)
    }
}