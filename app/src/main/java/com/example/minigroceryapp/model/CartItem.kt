package com.example.minigroceryapp.model

data class CartItem(
    val product: Product,
    var quantity: Int = 1
)