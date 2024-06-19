package com.pelletierantoine.mysephoratest.domain.models

data class ProductWithReviews(
    val productName: String,
    val description: String,
    val price: Float,
    val imageUrls: ImageUrls,
    val brand: Brand,
    val reviews: List<Review>,
    var reviewsExpanded: Boolean = false
)
