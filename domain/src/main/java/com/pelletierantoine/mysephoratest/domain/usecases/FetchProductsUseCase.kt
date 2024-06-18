package com.pelletierantoine.mysephoratest.domain.usecases

import com.pelletierantoine.mysephoratest.domain.common.NoParamsSuspendingUseCase
import com.pelletierantoine.mysephoratest.domain.models.Product
import com.pelletierantoine.mysephoratest.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher

class FetchProductsUseCase(
    override val dispatcher: CoroutineDispatcher,
    private val repository: ProductsRepository
) : NoParamsSuspendingUseCase<List<Product>>() {

    override suspend fun run(): Result<List<Product>> = runCatching {
        repository.fetchProducts()
    }
}