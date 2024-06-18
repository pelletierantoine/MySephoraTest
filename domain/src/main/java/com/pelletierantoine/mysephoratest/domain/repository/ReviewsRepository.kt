package com.pelletierantoine.mysephoratest.domain.repository

import com.pelletierantoine.mysephoratest.domain.models.ReviewProduct

interface ReviewsRepository {
    suspend fun fetchReviews(): List<ReviewProduct>
    fun getReviews(): List<ReviewProduct>
}