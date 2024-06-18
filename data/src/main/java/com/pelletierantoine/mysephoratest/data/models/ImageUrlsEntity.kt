package com.pelletierantoine.mysephoratest.data.models

import com.squareup.moshi.Json

internal data class ImageUrlsEntity(
    @Json(name = "small") val small: String,
    @Json(name = "large") val large: String
)
