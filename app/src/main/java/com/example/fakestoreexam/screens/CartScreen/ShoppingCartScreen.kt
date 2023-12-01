package com.example.fakestoreexam.screens.CartScreen

// ShoppingCartScreen.kt

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fakestoreexam.data.Order
import com.example.fakestoreexam.screens.OrderHistoryScreen.OrderHistoryViewModel
import com.example.fakestoreexam.screens.OrderHistoryScreen.OrderIdGenerator.generateOrderId
import com.example.fakestoreexam.screens.OrderHistoryScreen.OrderUtils.getCurrentDateTime
import com.example.fakestoreexam.ui.theme.CompanyPurpleDark

@Composable
fun ShoppingCartScreen(
    shoppingCartViewModel: ShoppingCartViewModel,
    orderHistoryViewModel: OrderHistoryViewModel,
    onBackButtonClick: () -> Unit,
    navController: NavController
){
    val cartItems by shoppingCartViewModel.cartItems.collectAsState()
    val totalPrice = cartItems.sumByDouble { it.price.toDouble() }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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
                text = "SHOPPING CART",
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
            // Menu Icon
            Icon(
                Icons.Rounded.List,
                contentDescription = null, 
                modifier = Modifier
                    .size(35.dp)
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("orderHistoryScreen")
                    }
            )
        }

        // Product List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(cartItems) { product ->
                CartItem(
                    product = product,
                    shoppingCartViewModel = shoppingCartViewModel,
                    onRemoveClick = {
                        shoppingCartViewModel.removeItemFromCart(product)
                    }
                )
            }
        }
        // Total Price
        Text(
            text = "Total Price: $${totalPrice}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Button(
            onClick = {
                val order = Order(
                    orderId = generateOrderId(),
                    products = cartItems,
                    totalPrice = totalPrice,
                    orderDate = getCurrentDateTime()
                )
                Toast.makeText(context, "Purchase complete", Toast.LENGTH_SHORT).show()

                shoppingCartViewModel.clearCart()
                orderHistoryViewModel.addOrder(order)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(text = "Checkout")
        }
    }
}
