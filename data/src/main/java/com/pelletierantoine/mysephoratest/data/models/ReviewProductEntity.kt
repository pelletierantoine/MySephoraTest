package com.pelletierantoine.mysephoratest.data.models

import com.squareup.moshi.Json

internal data class ReviewProductEntity(
    @Json(name = "product_id") val productId: Long,
    @Json(name = "hide") val hide: Boolean,
    @Json(name = "reviews") val reviews: List<ReviewEntity>,
)