package com.pelletierantoine.mysephoratest.data.mappers

import com.pelletierantoine.mysephoratest.data.BaseTest
import com.pelletierantoine.mysephoratest.data.models.ReviewEntity
import com.pelletierantoine.mysephoratest.domain.models.Review
import org.junit.Test
import kotlin.test.assertEquals

class ReviewsMapperTest : BaseTest() {

    private val mapper: ReviewMapper by lazy { ReviewMapper() }

    private val reviewEntity = ReviewEntity(
        name = "Antoine PELLETIER",
        text = "Excellent produit !",
        rating = 5f
    )

    private val review = Review(
        name = "Antoine PELLETIER",
        text = "Excellent produit !",
        rating = 5f,
        showStars = true
    )

    @Test
    fun `from entity`() {
        val testObject = mapper.fromEntity(review)
        assertEquals(testObject, reviewEntity)
    }

    @Test
    fun `to entity`() {
        val testObject = mapper.toEntity(reviewEntity)
        assertEquals(testObject, review)
    }
}