package com.pelletierantoine.mysephoratest.domain

import com.pelletierantoine.mysephoratest.domain.usecases.FilterProductsByNameUseCase
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FilterProductsByNameUseCaseTest : BaseTest() {

    private val testProducts = listOf(
        productWithReviews.copy(productName = "Sephora Parfum"),
        productWithReviews.copy(productName = "Channel"),
        productWithReviews.copy(productName = "Sephora Maquillage")
    )

    private lateinit var filterProductsByNameUseCase: FilterProductsByNameUseCase

    override fun setUp() {
        super.setUp()
        filterProductsByNameUseCase = FilterProductsByNameUseCase()
    }

    @Test
    fun `should return two products filtered`() = runTest {
        val expected = listOf(
            productWithReviews.copy(productName = "Sephora Parfum"),
            productWithReviews.copy("Sephora Maquillage")
        )

        val result = filterProductsByNameUseCase.execute(FilterProductsByNameUseCase.Params("Sephora", testProducts))
        assertEquals(expected, result)
        assertEquals(2, result.size)
    }
}