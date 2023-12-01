package com.example.fakestoreexam


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fakestoreexam.data.Product
import com.example.fakestoreexam.screens.DetailScreen.ProductDetailsScreen
import com.example.fakestoreexam.screens.DetailScreen.ProductDetailsViewModel
import com.example.fakestoreexam.screens.ProductListScreen.ProductListScreen
import com.example.fakestoreexam.screens.ListScreen.ProductListViewModel
import com.example.fakestoreexam.screens.CartScreen.ShoppingCartScreen
import com.example.fakestoreexam.screens.CartScreen.ShoppingCartViewModel
import com.example.fakestoreexam.screens.OrderHistoryScreen.OrderHistoryScreen
import com.example.fakestoreexam.screens.OrderHistoryScreen.OrderHistoryViewModel
import com.example.fakestoreexam.ui.theme.FakeStoreExamTheme

class MainActivity : ComponentActivity() {
    private val _productListViewModel = ProductListViewModel()
    private val _productDetailsViewModel = ProductDetailsViewModel()
    private val _shoppingCartViewModel = ShoppingCartViewModel()
    private val _orderHistoryViewModel: OrderHistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreExamTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "ProductListScreen"
                ) {
                    composable(route = "ProductListScreen") {
                        ProductListScreen(
                            viewModel = _productListViewModel,
                            onProductClick = { productId ->
                                navController.navigate("productDetailsScreen/$productId")
                            },
                            navController = navController,
                            viewModelDetails = _productDetailsViewModel
                        )
                    }
                    composable(
                        route = "productDetailsScreen/{productId}",
                        arguments = listOf(
                            navArgument(name = "productId") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getInt("productId") ?: -1

                        val productDetailsViewModel: ProductDetailsViewModel = viewModel()

                        LaunchedEffect(productId) {
                            productDetailsViewModel.setSelectedProduct(productId)
                        }

                        ProductDetailsScreen(
                            viewModel = productDetailsViewModel,
                            shoppingCartViewModel = _shoppingCartViewModel,
                            onBackButtonClick = { navController.popBackStack() },
                            navController = navController
                        )
                    }
                    composable(
                        route = "shoppingCartScreen"
                    ) {
                        ShoppingCartScreen(
                            shoppingCartViewModel = _shoppingCartViewModel,
                            orderHistoryViewModel = _orderHistoryViewModel,
                            onBackButtonClick = { navController.popBackStack() },
                            navController = navController
                        )
                    }
                    composable(route = "orderHistoryScreen") {
                        OrderHistoryScreen(
                            orderHistoryViewModel = _orderHistoryViewModel,
                            onBackButtonClick = { navController.popBackStack() },
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
