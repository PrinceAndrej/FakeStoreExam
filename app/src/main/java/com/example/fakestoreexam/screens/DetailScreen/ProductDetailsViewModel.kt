package com.example.fakestoreexam.screens.DetailScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreexam.data.Product
import com.example.fakestoreexam.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {
    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct = _selectedProduct.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    suspend fun setSelectedProduct(productId: Int) {
        viewModelScope.launch {
            _loading.value = true
            _selectedProduct.value = ProductRepository.getSingleProduct(productId)
            _loading.value = false
        }
    }
}