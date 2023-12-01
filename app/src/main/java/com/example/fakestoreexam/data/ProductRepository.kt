package com.example.fakestoreexam.data

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductRepository {



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
        return productService.getProducts().products
    }

    suspend fun getSingleProduct(id: Int): Product? {
        val response = productService.getSingleProduct(id = id)

        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
