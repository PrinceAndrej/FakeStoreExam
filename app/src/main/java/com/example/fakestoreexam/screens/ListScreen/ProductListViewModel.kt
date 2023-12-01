package com.example.fakestoreexam.screens.ListScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreexam.data.Product
import com.example.fakestoreexam.data.ProductRepository
import com.example.fakestoreexam.screens.DetailScreen.ProductDetailsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class ProductListViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    private val _showOnlyFavorites = MutableStateFlow(false)
    val showOnlyFavorites: StateFlow<Boolean> = _showOnlyFavorites.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct = _selectedProduct.asStateFlow()


    private val _nameSearchField = MutableStateFlow("")
    val nameSearchField: StateFlow<String> = _nameSearchField.asStateFlow()

    val filteredProductList = combine(
        products, nameSearchField
    ) { productList, nameSearchFieldValue ->
        productList.filter { product ->
            (product.title.contains(nameSearchFieldValue, ignoreCase = true) || nameSearchFieldValue.isBlank())
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())





    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _loading.value = true
            _products.value = ProductRepository.getProducts()
            _loading.value = false
        }
    }


    fun updateNameSearchField(text: String) {
        _nameSearchField.value = text
    }

    }


