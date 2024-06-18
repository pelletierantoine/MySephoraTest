package com.pelletierantoine.mysephoratest.data.repository

import com.pelletierantoine.mysephoratest.data.mappers.ReviewProductMapper
import com.pelletierantoine.mysephoratest.data.service.ReviewsApi
import com.pelletierantoine.mysephoratest.domain.models.ReviewProduct
import com.pelletierantoine.mysephoratest.domain.repository.ReviewsRepository

internal class ReviewsRepositoryImpl(
    private val service: ReviewsApi,
    private val reviewProductMapper: ReviewProductMapper
) : ReviewsRepository {

    private var reviewsByProduct = mutableListOf<ReviewProduct>()

    override suspend fun fetchReviews(): List<ReviewProduct> {
        return service.fetchReviewsByProduct()
            .map(reviewProductMapper::toEntity)
            .also {
                reviewsByProduct.clear()
                reviewsByProduct.addAll(it)
            }
    }

    override fun getReviews(): List<ReviewProduct> {
        return reviewsByProduct
    }
}