package com.pelletierantoine.mysephoratest.domain.models

data class ProductWithReviews(
    val productName: String,
    val numberRating: String,
    val brand: String,
    val description: String,
    val priceFormatted: String,
    val imageUrl: String,
    val reviews: List<Review>,
    var reviewsExpanded: Boolean = false,
    var rating: Float
)
