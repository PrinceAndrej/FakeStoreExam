package com.example.fakestoreexam.data

data class Product(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val category: String,
    val thumbnail: String,
    val rating: Double,
)
data class ProductsResponse(
    val products: List<Product>
)





