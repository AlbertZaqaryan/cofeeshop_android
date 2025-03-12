package com.example.androidcoffeshop

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("product_list/")
    suspend fun getProducts(): List<Product>

    @GET("category_list/")
    suspend fun getCategories(): List<Category>
}
