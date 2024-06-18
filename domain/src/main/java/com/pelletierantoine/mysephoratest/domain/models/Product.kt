package com.pelletierantoine.mysephoratest.domain.models

data class Product(
    val productId: Long,
    val productName: String,
    val description: String,
    val price: Float,
    val imageUrls: ImageUrls,
    val brand: Brand,
    val isProductSet: Boolean,
    val isSpecialBrand: Boolean
)
