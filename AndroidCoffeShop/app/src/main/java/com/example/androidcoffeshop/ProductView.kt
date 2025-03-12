package com.example.androidcoffeshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class Product(
    val id: Int,
    val name: String,
    val image: String,
    val rating: Double,
    val description: String,
    val price_S: Double,
    val price_M: Double,
    val price_L: Double,
    val including: String,
    val category: Int
)


class ProductsViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    var FlitText: MutableLiveData<String> = MutableLiveData("")

    private val apiService = RetrofitInstance.api

    fun getProducts() {
        viewModelScope.launch {
            try {
                val response = apiService.getProducts()
                _products.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
