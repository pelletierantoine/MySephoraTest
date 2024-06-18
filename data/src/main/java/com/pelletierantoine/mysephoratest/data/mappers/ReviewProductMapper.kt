package com.pelletierantoine.mysephoratest.data.mappers

import com.pelletierantoine.mysephoratest.data.models.ReviewProductEntity
import com.pelletierantoine.mysephoratest.domain.models.ReviewProduct

internal class ReviewProductMapper(
    private val reviewMapper: ReviewMapper
) : Mapper<ReviewProductEntity, ReviewProduct> {
    override fun fromEntity(entity: ReviewProduct): ReviewProductEntity {
        return ReviewProductEntity(
            productId = entity.productId,
            hide = entity.hide,
            reviews = entity.reviews.map(reviewMapper::fromEntity)
        )
    }

    override fun toEntity(data: ReviewProductEntity): ReviewProduct {
        return ReviewProduct(
            productId = data.productId,
            hide = data.hide ?: false,
            reviews = data.reviews.map(reviewMapper::toEntity)
        )
    }
}