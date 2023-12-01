package com.example.fakestoreexam.screens.OrderHistoryScreen

import java.text.SimpleDateFormat
import java.util.Date

object OrderUtils {
    private var counter = 0

    fun generateOrderId(): Int {
        return counter++
    }

    fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy '\nTime\n'HH:mm:ss z")
        val currentDateAndTime = sdf.format(Date())
        return currentDateAndTime

    }
}
