package com.pelletierantoine.mysephoratest.domain

import com.pelletierantoine.mysephoratest.domain.models.Product
import com.pelletierantoine.mysephoratest.domain.repository.ProductsRepository
import com.pelletierantoine.mysephoratest.domain.usecases.FetchProductsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FetchProductsUseCaseTest : BaseTest() {

    private val mockProductsRepository: ProductsRepository = mockk()

    private lateinit var fetchProductsUseCase: FetchProductsUseCase

    override fun setUp() {
        super.setUp()
        fetchProductsUseCase = FetchProductsUseCase(
            testDispatcher,
            mockProductsRepository
        )
    }

    @Test
    fun `should return products if repository returns it`() = runTest {
        val mockProducts: List<Product> = mockk()
        coEvery { mockProductsRepository.fetchProducts() } returns mockProducts

        val result = fetchProductsUseCase()
        assertEquals(mockProducts, result.getOrNull())
    }

    @Test
    fun `should return error if repository throws exception`() = runTest {
        val exception = IllegalStateException()
        coEvery { mockProductsRepository.fetchProducts() } throws exception

        val result = fetchProductsUseCase()
        assertEquals(exception, result.exceptionOrNull())
    }
}