package com.pelletierantoine.mysephoratest.data.models

import com.squareup.moshi.Json

internal data class ProductEntity(
    @Json(name = "product_id") val productId: Long,
    @Json(name = "product_name") val productName: String,
    @Json(name = "description") val description: String,
    @Json(name = "price") val price: Float,
    @Json(name = "images_url") val imageUrls: ImageUrlsEntity,
    @Json(name = "c_brand") val brand: BrandEntity,
    @Json(name = "is_productSet") val isProductSet: Boolean,
    @Json(name = "is_special_brand") val isSpecialBrand: Boolean,
)