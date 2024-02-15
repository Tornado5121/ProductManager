package com.zhadko.productsmanager.errors

data class ProductError(
    val customerMessage: String = "Something went wrong, try again, please",
    val techMessage: String,
) : Exception()