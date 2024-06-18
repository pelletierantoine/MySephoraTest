package com.pelletierantoine.mysephoratest.domain.repository

import com.pelletierantoine.mysephoratest.domain.models.ReviewProduct

interface ReviewsRepository {
    suspend fun fetchReviewsByProduct(productId: Long): List<ReviewProduct>
    fun getReviewsByProduct(productId: Long): List<ReviewProduct>
}