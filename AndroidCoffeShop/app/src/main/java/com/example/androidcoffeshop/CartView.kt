package com.example.androidcoffeshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class Cart(
    val id: Int,
    val count:Int,
    val size:String,
    val product: Int
)



class CartViewModel  : ViewModel() {
    private val _cart = MutableLiveData<List<Cart>>()
    val cart: LiveData<List<Cart>> get() = _cart

    private val apiService = RetrofitInstance.api

    fun getCart() {
        viewModelScope.launch {
            try {
                val response = apiService.getCart()
                _cart.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun addCount(id: Int, currentCount: Int) {
        viewModelScope.launch {
            try {
                val newCount = (currentCount + 1).toInt() // Convert to Int for API
                withContext(Dispatchers.IO) {
                    apiService.addCount(id, CartUpdateRequest(id, newCount, "M", 1))
                }
                getCart() // Refresh the cart list after update
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun subCount(id: Int, currentCount: Int) {
        if (currentCount == 0) return // Prevent underflow error

        viewModelScope.launch {
            try {
                val newCount = (currentCount - 1).toInt()
                withContext(Dispatchers.IO) {
                    if (newCount > 0) {
                        apiService.subCount(id, CartUpdateRequest(id, newCount, "M", 1))
                    } else {
                        apiService.delCount(id) // Delete item if count reaches 0
                    }
                }
                getCart() // Refresh cart list after update
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}