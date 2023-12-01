package com.example.fakestoreexam.screens.OrderHistoryScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fakestoreexam.data.Order

@Composable
fun OrderItem(order: Order, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Display order information
            Text(text = "Order ID: ${order.orderId}", fontWeight = FontWeight.Bold)
            Text(text = "Total Price: $${order.totalPrice}")
            Text(text = "Order Date: ${order.orderDate}")

           // Display order products
            order.products.forEach { product ->
                Text(text = product.title)
            }
        }
    }
}


object OrderIdGenerator {
    private var counter = 0

    fun generateOrderId(): Int {
        return counter++
    }
}