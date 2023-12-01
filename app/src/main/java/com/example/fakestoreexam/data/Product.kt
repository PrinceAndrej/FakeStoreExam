package com.example.fakestoreexam.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val category: String,
    val thumbnail: String,
    val rating: Double
)

data class ProductsResponse(
    val products: List<Product>
)





