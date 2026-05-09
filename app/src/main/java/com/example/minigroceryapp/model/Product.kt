package com.example.minigroceryapp.model

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: String,
    val imageResId: Int
)