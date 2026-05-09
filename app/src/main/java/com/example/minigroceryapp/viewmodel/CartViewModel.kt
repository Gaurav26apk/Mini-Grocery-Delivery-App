package com.example.minigroceryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minigroceryapp.model.CartItem
import com.example.minigroceryapp.model.Product

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    fun addToCart(product: Product) {
        val list = _cartItems.value ?: mutableListOf()
        val existingItem = list.find { it.product.id == product.id }

        if (existingItem != null) {
            existingItem.quantity++
        } else {
            list.add(CartItem(product, 1))
        }

        _cartItems.value = list.toMutableList()
    }

    fun increaseQuantity(position: Int) {
        val list = _cartItems.value ?: return
        list[position].quantity++
        _cartItems.value = list.toMutableList()
    }

    fun decreaseQuantity(position: Int) {
        val list = _cartItems.value ?: return

        if (list[position].quantity > 1) {
            list[position].quantity--
        } else {
            list.removeAt(position)
        }

        _cartItems.value = list.toMutableList()
    }

    fun removeItem(position: Int) {
        val list = _cartItems.value ?: return
        list.removeAt(position)
        _cartItems.value = list.toMutableList()
    }

    fun getTotalAmount(): Double {
        return _cartItems.value?.sumOf {
            it.product.price * it.quantity
        } ?: 0.0
    }

    fun clearCart() {
        _cartItems.value = mutableListOf()
    }

    companion object {
        val instance: CartViewModel by lazy {
            CartViewModel()
        }
    }
}