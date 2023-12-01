package com.example.fakestoreexam.screens.CartScreen

import androidx.lifecycle.ViewModel
import com.example.fakestoreexam.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ShoppingCartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<Product>>(emptyList())
    val cartItems: StateFlow<List<Product>> = _cartItems

    fun addItemToCart(product: Product) {
        viewModelScope.launch {
            val updatedCart = _cartItems.value.toMutableList()
            updatedCart.add(product)
            _cartItems.value = updatedCart
        }
    }
    fun removeItemFromCart(product: Product) {
        val updatedCart = _cartItems.value.toMutableList()
        updatedCart.remove(product)
        _cartItems.value = updatedCart
    }
    fun clearCart() {
        _cartItems.value = emptyList()
    }
}
