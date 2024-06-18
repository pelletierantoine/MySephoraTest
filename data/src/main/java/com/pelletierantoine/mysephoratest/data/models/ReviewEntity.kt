package com.pelletierantoine.mysephoratest.data.models

import com.squareup.moshi.Json

internal data class ReviewEntity(
    @Json(name = "name") val name: String? = null,
    @Json(name = "text") val text: String ?= null,
    @Json(name = "rating") val rating: Float? = null,
)

