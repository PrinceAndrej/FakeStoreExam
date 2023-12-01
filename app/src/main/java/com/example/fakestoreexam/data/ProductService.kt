package com.example.fakestoreexam.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Path
interface ProductService {
    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>

    @GET("products/{id}")
    suspend fun getSingleProduct(@Path("id") id: Int): Response<Product>
}




