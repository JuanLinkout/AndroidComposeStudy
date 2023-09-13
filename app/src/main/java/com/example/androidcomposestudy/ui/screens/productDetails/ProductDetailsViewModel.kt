package com.example.androidcomposestudy.ui.screens.productDetails

import androidx.lifecycle.ViewModel
import com.example.androidcomposestudy.utils.generateProductList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductDetailsViewModel: ViewModel() {
    private val _selectedImage = MutableStateFlow(generateProductList(1)[0].imageURL)
    val selectedImage: StateFlow<String> = _selectedImage

    fun onImageChange(updatedImageUrl: String) {
        _selectedImage.value = updatedImageUrl
    }
}