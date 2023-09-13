package com.example.androidcomposestudy.domain.entities

data class ProductListItem(
    val name: String,
    val category: String,
    val price: Double,
    val imageURL: String,
    val discountPercentage: Double
)
