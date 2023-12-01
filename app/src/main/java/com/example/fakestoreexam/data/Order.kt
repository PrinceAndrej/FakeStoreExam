package com.example.fakestoreexam.data

data class Order(
    val orderId: Int,
    val products: List<Product>,
    val totalPrice: Double,
    val orderDate: String
)
