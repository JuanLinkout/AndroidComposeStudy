package com.example.androidcomposestudy.utils

import com.example.androidcomposestudy.domain.entities.ProductListItem
import kotlin.random.Random

fun generateProductList(numberOfItems: Int): List<ProductListItem> {
    val productList = mutableListOf<ProductListItem>()

    for (i in 1..numberOfItems) {
        val name = "Product $i"
        val category = "Category ${i % 5}" // Assume 5 categories
        val price = Random.nextDouble(10.0, 100.0) // Random price between 10 and 100
        val discountPercentage = Random.nextDouble(0.0, 50.0) // Random discount percentage between 0 and 50

        // Generate a random width between 200 and 300 for the image
        val imageWidth = Random.nextInt(200, 301)
        val imageURL = "https://source.unsplash.com/random/${imageWidth}x300"

        val product = ProductListItem(name, category, price, imageURL, discountPercentage)
        productList.add(product)
    }

    return productList
}