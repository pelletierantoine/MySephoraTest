package com.pelletierantoine.mysephoratest.data.repository

import com.pelletierantoine.mysephoratest.data.mappers.ProductMapper
import com.pelletierantoine.mysephoratest.data.service.ProductsApi
import com.pelletierantoine.mysephoratest.domain.models.Product
import com.pelletierantoine.mysephoratest.domain.repository.ProductsRepository

internal class ProductsRepositoryImpl(
    private val service: ProductsApi,
    private val productsMapper: ProductMapper
) : ProductsRepository {

    private val products = mutableListOf<Product>()

    override suspend fun fetchProducts(): List<Product> {
        return service.fetchProducts()
            .map(productsMapper::toEntity)
            .also {
                products.clear()
                products.addAll(it)
            }
    }

    override fun getProducts(): List<Product> {
        return products
    }
}