package com.example.fakestoreexam.data

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

object ProductRepository {

    private val productDao by lazy { _appDatabase.productDao() }

    private lateinit var _appDatabase: AppDatabase

    fun initDatabase(context: Context) {
        _appDatabase = databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }


    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val productService = retrofit.create(ProductService::class.java)

    suspend fun getProducts(): List<Product> {
        try {
            val response = productService.getProducts()

            if (!response.isSuccessful) {
                throw Exception("Response was not successful: HTTP ${response.code()}")
            }

            val products = response.body()?.products ?: throw Exception("Response body was empty")

            // Insert products into the local database
            productDao.insertProducts(products)

            return productDao.getAllProducts()
        } catch (e: Exception) {
            Log.e("ProductRepository", "Failed to get list of products", e)

            // Return products from the local database in case of an error
            return productDao.getAllProducts()
        }
    }

    suspend fun getSingleProduct(id: Int): Product? {
        try {
            val response = productService.getSingleProduct(id)

            if (!response.isSuccessful) {
                throw Exception("Response was not successful: HTTP ${response.code()}")
            }

            val product = response.body() ?: throw Exception("response.body() was empty")


            // Updates the imageUrl to a real one


            return productDao.getProductById(id)
        } catch (e: Exception) {
            Log.e("ProductRepository", "Failed to get product details", e)

            // Return product details from the local database in case of an error
            return productDao.getProductById(id)
        }
    }

}
