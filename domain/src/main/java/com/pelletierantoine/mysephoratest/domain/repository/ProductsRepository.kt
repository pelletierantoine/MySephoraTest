package com.pelletierantoine.mysephoratest.domain.repository

import com.pelletierantoine.mysephoratest.domain.models.Product

interface ProductsRepository {
    suspend fun fetchProducts(): List<Product>
    fun getProducts(): List<Product>
}