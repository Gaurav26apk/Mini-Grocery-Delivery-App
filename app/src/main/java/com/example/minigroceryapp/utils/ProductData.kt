package com.example.minigroceryapp.utils

import com.example.minigroceryapp.R
import com.example.minigroceryapp.model.Product

object ProductData {

    fun getProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Milk",
                price = 30.0,
                category = "Dairy",
                imageResId = R.drawable.milk
            ),
            Product(
                id = 2,
                name = "Bread",
                price = 25.0,
                category = "Bakery",
                imageResId = R.drawable.bread
            ),
            Product(
                id = 3,
                name = "Apple",
                price = 120.0,
                category = "Fruits",
                imageResId = R.drawable.apple
            ),
            Product(
                id = 4,
                name = "Rice",
                price = 60.0,
                category = "Groceries",
                imageResId = R.drawable.rice
            ),
            Product(
                id = 5,
                name = "Mango",
                price = 500.0,
                category = "Fruits",
                imageResId = R.drawable.mango
            ),

            Product(
                id = 6,
                name = "Paneer",
                price = 200.0,
                category = "Dairy",
                imageResId = R.drawable.paneer
            ),

            Product(
                id = 7,
                name = "Wheat",
                price = 80.0,
                category = "Groceries",
                imageResId = R.drawable.wheat
            )

        )
    }
}