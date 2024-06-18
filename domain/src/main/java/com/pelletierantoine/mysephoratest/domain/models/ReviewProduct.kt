package com.pelletierantoine.mysephoratest.domain.models

data class ReviewProduct(
    val productId: Long,
    val hide: Boolean,
    val reviews: List<Review>,
)
