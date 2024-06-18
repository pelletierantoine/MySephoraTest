package com.pelletierantoine.mysephoratest.data.models

import com.squareup.moshi.Json

internal data class ReviewEntity(
    @Json(name = "name") val name: String,
    @Json(name = "text") val text: String,
    @Json(name = "rating") val rating: Float,
)

