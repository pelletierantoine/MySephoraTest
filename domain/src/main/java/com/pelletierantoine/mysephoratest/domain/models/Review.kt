package com.pelletierantoine.mysephoratest.domain.models

data class Review(
    val name: String,
    val text: String,
    val rating: Float,
    val showStars: Boolean
)
