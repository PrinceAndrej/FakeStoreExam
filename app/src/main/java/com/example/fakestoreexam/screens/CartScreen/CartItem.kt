package com.example.fakestoreexam.screens.CartScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.fakestoreexam.data.Product

@Composable
fun CartItem(
    product: Product,
    shoppingCartViewModel: ShoppingCartViewModel,
    onRemoveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Product Image
        Image(
            painter = rememberImagePainter(data = product.thumbnail),
            contentDescription = "Product Image",
            modifier = Modifier
                .size(108.dp, 108.dp)
                .background(color = Color.Gray)
        )

        // Product Details
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = product.title.capitalize(),
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Category: ${product.category.capitalize()}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Price: $${product.price}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
        }

        // Remove from Cart Icon
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = "Remove from Cart",
            modifier = Modifier
                .size(35.dp)
                .clickable {
                    onRemoveClick()
                    shoppingCartViewModel.removeItemFromCart(product)
                }
                .padding(end = 8.dp)
        )
    }
}
