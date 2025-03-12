package com.example.androidcoffeshop

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class Category(
    val id: Int,
    val name: String
)

class CategoryViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    var selIn: MutableLiveData<Int> = MutableLiveData(0)

    private val apiService = RetrofitInstance.api

    fun getCategories() {
        viewModelScope.launch {
            try {
                val response = apiService.getCategories()
                _categories.postValue(response) // Используем `postValue` для обновления LiveData
                Log.d("API", "Categories: $response")
            } catch (e: Exception) {
                Log.e("API", "Error fetching categories", e)
            }
        }
    }
}
