package com.pelletierantoine.mysephoratest.data.service

import com.pelletierantoine.mysephoratest.data.models.ReviewProductEntity
import retrofit2.http.GET
import retrofit2.http.Headers

internal interface ReviewsApi {

    @Headers(
        "Content-Type: application/json; charset=utf-8",
        "Accept: application/json"
    )
    @GET("reviews.json")
    suspend fun fetchReviewsByProduct(): List<ReviewProductEntity>
}