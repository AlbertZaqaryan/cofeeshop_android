package com.example.androidcoffeshop

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

data class CartUpdateRequest(
    val id: Int,
    val count:Int,
    val size:String,
    val product: Int
)

interface ApiService {
    @GET("product_list/")
    suspend fun getProducts(): List<Product>

    @GET("category_list/")
    suspend fun getCategories(): List<Category>

    @GET("cart_list/")
    suspend fun getCart(): List<Cart>

    @PUT("cart_list/{id}/")
    suspend fun addCount(@Path("id") id: Int, @Body request: CartUpdateRequest): List<Cart> // Fixed return type

    @PUT("cart_list/{id}/")
    suspend fun subCount(@Path("id") id: Int, @Body request: CartUpdateRequest): List<Cart> // Fixed return type

    @DELETE("cart_list/{id}/")
    suspend fun delCount(@Path("id") id: Int): Void // Removed Call<Void> and used proper return type
}