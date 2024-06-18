package com.pelletierantoine.mysephoratest.data.service

import com.pelletierantoine.mysephoratest.data.models.ProductEntity
import retrofit2.http.GET
import retrofit2.http.Headers

internal interface ProductsApi {

    @Headers(
        "Content-Type: application/json; charset=utf-8",
        "Accept: application/json"
    )
    @GET("items.json")
    suspend fun fetchProducts(): List<ProductEntity>
}