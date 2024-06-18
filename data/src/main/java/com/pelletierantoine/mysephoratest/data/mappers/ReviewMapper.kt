package com.pelletierantoine.mysephoratest.data.mappers

import com.pelletierantoine.mysephoratest.data.models.ReviewEntity
import com.pelletierantoine.mysephoratest.domain.models.Review

internal class ReviewMapper : Mapper<ReviewEntity, Review> {
    override fun fromEntity(entity: Review): ReviewEntity {
        return ReviewEntity(
            name = entity.name,
            text = entity.text,
            rating = entity.rating
        )
    }

    override fun toEntity(data: ReviewEntity): Review {
        return Review(
            name = data.name,
            text = data.text,
            rating = data.rating
        )
    }
}