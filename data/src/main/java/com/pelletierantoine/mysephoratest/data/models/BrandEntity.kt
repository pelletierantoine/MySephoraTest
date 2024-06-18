package com.pelletierantoine.mysephoratest.data.models

import com.squareup.moshi.Json

internal data class BrandEntity(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String
)
