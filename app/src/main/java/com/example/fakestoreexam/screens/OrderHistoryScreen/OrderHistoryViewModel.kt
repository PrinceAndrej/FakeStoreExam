package com.example.fakestoreexam.screens.OrderHistoryScreen

import androidx.lifecycle.ViewModel
import com.example.fakestoreexam.data.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class OrderHistoryViewModel  : ViewModel() {
    private val _orderDetails = MutableStateFlow<Order?>(null)
    val orderDetails: StateFlow<Order?> = _orderDetails.asStateFlow()


    private val _orderHistory = MutableStateFlow<List<Order>>(emptyList())
    val orderHistory: StateFlow<List<Order>> = _orderHistory.asStateFlow()

    // Function to add a new order to the history
    fun addOrder(order: Order) {
        val updatedOrderHistory = _orderHistory.value.toMutableList()
        updatedOrderHistory.add(order)
        _orderHistory.value = updatedOrderHistory
    }


}
