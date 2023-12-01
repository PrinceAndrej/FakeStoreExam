package com.example.fakestoreexam.screens.DetailScreen


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fakestoreexam.data.Product
import com.example.fakestoreexam.screens.CartScreen.ShoppingCartViewModel
import com.example.fakestoreexam.ui.theme.CompanyPurpleDark
import com.example.fakestoreexam.ui.theme.CompanyPurpleLight
import kotlinx.coroutines.launch

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel,
    shoppingCartViewModel: ShoppingCartViewModel,
    onBackButtonClick: () -> Unit,
    navController: NavController,
) {
    val loading by viewModel.loading.collectAsState()
    val products by viewModel.selectedProduct.collectAsState()
    val context = LocalContext.current


    if (loading) {
        CircularProgressIndicator()
    } else {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackButtonClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                               contentDescription ="Back button",
                            tint = CompanyPurpleDark
                        )
                    }
                    Text(
                        text = "PRODUCT DETAILS",
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
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Shopping cart",
                            tint = CompanyPurpleDark,
                            modifier = Modifier
                                .size(35.dp)
                                .padding(8.dp)
                                .clickable{
                                    navController.navigate("shoppingCartScreen")
                                }
                        )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                       ,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                   Box(modifier = Modifier.shadow(
                       elevation = 20.dp,
                       shape = RoundedCornerShape(10)
                   ) ){
                       AsyncImage(
                           modifier = Modifier
                               .size(250.dp, 250.dp)
                               .background(color = Color.Gray)
                               .border(
                                   width = 2.dp,
                                   color = CompanyPurpleDark,
                                   shape = MaterialTheme.shapes.medium
                               ),
                           model = products?.thumbnail,
                           alignment = Alignment.Center,
                           contentScale = ContentScale.Crop,
                           contentDescription = "Image of ${products?.title}"
                       )
                   }
                    Text(
                        text = products?.title ?: "No Title",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    Text(
                        text = products?.description?: "No Description",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                            text = products?.category?.capitalize() ?: "No Category",
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ){
                        Row(
                            modifier = Modifier
                                .border(
                                    width = 2.dp,
                                    color = CompanyPurpleDark,
                                    shape = MaterialTheme.shapes.medium,
                                )
                                .padding(2.dp)
                        ){
                            for (x in 1..(products?.rating?.toInt() ?: 0)) {
                                Icon(
                                    Icons.Rounded.Star,
                                    contentDescription = "Star",
                                    tint = CompanyPurpleLight
                                )
                            }
                        }
                        Text(
                            text = "${products?.rating} stars",
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Divider(modifier = Modifier
                            .padding(bottom = 16.dp)
                            .background(CompanyPurpleDark)
                            .fillMaxWidth())
                       Row(
                           modifier = Modifier.fillMaxWidth()
                       ) {
                           Text(
                               text = "Price: $${products?.price}",
                               fontWeight = FontWeight.Bold,
                               fontSize = 20.sp,
                           )
                           Spacer(modifier = Modifier.width(100.dp))
                           Button(
                               onClick = {
                                   viewModel.selectedProduct.value?.let { product ->
                                       shoppingCartViewModel.addItemToCart(product)
                                       Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
                                   }
                               }
                           ) {
                               Text(text = "Add to Cart")
                           }
                       }
                    }
                }
            }
        }
    }
}





