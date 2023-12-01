package com.example.fakestoreexam.screens.OrderHistoryScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fakestoreexam.ui.theme.CompanyPurpleDark

@Composable
fun OrderHistoryScreen(
    orderHistoryViewModel: OrderHistoryViewModel,
    onBackButtonClick: () -> Unit,
    navController: NavController
) {
    val orderHistory by orderHistoryViewModel.orderHistory.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onBackButtonClick() }
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back button",
                    tint = CompanyPurpleDark
                )
            }
            Text(
                text = "ORDER HISTORY",
                fontWeight = FontWeight.Bold,
                letterSpacing = 5.sp,
                color = CompanyPurpleDark,
            )
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "Home",
                tint = CompanyPurpleDark,
                modifier = Modifier
                    .size(35.dp)
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("ProductListScreen")
                    }
            )
        }

        // Order List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(orderHistory) { order ->
                OrderItem(order = order) {

                }
            }
        }
    }
}
