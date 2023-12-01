package com.example.fakestoreexam.screens.ProductListScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon

import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fakestoreexam.screens.DetailScreen.ProductDetailsViewModel
import com.example.fakestoreexam.screens.ProductItem.ProductItem
import com.example.fakestoreexam.screens.ListScreen.ProductListViewModel
import com.example.fakestoreexam.ui.theme.CompanyPurpleDark


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel,
    onProductClick: (productId: Int) -> Unit = {},
    navController: NavController,
    viewModelDetails: ProductDetailsViewModel
) {
    val loading by viewModel.loading.collectAsState()
     val filteredProducts by viewModel.filteredProductList.collectAsState()
    val searchTitle by viewModel.nameSearchField.collectAsState()



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
                Text(
                    text = "PRODUCTS",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = CompanyPurpleDark,
                    letterSpacing = 20.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = searchTitle,
                        onValueChange = { text -> viewModel.updateNameSearchField(text) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                            .height(60.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            textColor = Color.Black,
                            cursorColor = Color.Gray,
                            focusedIndicatorColor = Color.Red
                        ),
                        label = { Text("Product Title") }
                    )
                    Icon(
                        Icons.Rounded.ShoppingCart,
                        contentDescription = null,
                        modifier = Modifier
                            .size(35.dp)
                            .clickable {
                                navController.navigate("shoppingCartScreen")
                            }
                            .padding(end = 8.dp)
                    )
                    Icon(
                        Icons.Rounded.List,
                        contentDescription = null,
                        modifier = Modifier
                            .size(35.dp)
                            .clickable {
                                navController.navigate("orderHistoryScreen")
                            }
                            .padding(end = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .background(color = CompanyPurpleDark)
                        .border(5.dp, CompanyPurpleDark, RoundedCornerShape(16.dp))
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredProducts) { product ->
                            ProductItem(
                                product = product,
                                onClick = { onProductClick(product.id) },
                            )
                        }
                    }
                }
            }
        }
    }