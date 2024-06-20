package com.pelletierantoine.mysephoratest.domain

import com.pelletierantoine.mysephoratest.domain.models.Brand
import com.pelletierantoine.mysephoratest.domain.models.ImageUrls
import com.pelletierantoine.mysephoratest.domain.models.Product
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import com.pelletierantoine.mysephoratest.domain.models.Review
import com.pelletierantoine.mysephoratest.domain.models.ReviewProduct
import com.pelletierantoine.mysephoratest.domain.usecases.FetchProductsUseCase
import com.pelletierantoine.mysephoratest.domain.usecases.FetchReviewsUseCase
import com.pelletierantoine.mysephoratest.domain.usecases.ProductsWithReviewsAssociatedUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductsWithReviewsAssociatedUseCaseTest : BaseTest() {

    private val mockFetchProductsUseCase: FetchProductsUseCase = mockk()
    private val mockFetchReviewsUseCase: FetchReviewsUseCase = mockk()

    private lateinit var productsWithAssociatedReviewsUseCase: ProductsWithReviewsAssociatedUseCase

    private val product = Product(
        productId = 12,
        productName = "Size Up - Mascara Volume Extra Large Immédiat",
        description = "Craquez pour le Mascara Size Up de Sephora Collection : Volume XXL immédiat, cils ultra allongés et recourbés ★ Formule vegan longue tenue ✓",
        price = 140f,
        imageUrls = ImageUrls(
            small = "https://dev.sephora.fr/on/demandware.static/-/Library-Sites-SephoraV2/default/dw521a3f33/brands/institbanner/SEPHO_900_280_institutional_banner_20210927_V2.jpg",
            large = ""
        ),
        brand = Brand.SEPHORA,
        isProductSet = false,
        isSpecialBrand = false
    )

    private val reviewProduct = ReviewProduct(
        productId = 12,
        hide = false,
        reviews = listOf(Review(name = "Antoine PELLETIER", text = "Excellent produit !", rating = 4f))
    )

    private val productWithReviews = ProductWithReviews(
        productName = product.productName,
        description = product.description,
        numberRating = "1 avis",
        brand = Brand.SEPHORA.entireName,
        priceFormatted = product.price.toFormattedPrice(),
        imageUrl = "https://dev.sephora.fr/on/demandware.static/-/Library-Sites-SephoraV2/default/dw521a3f33/brands/institbanner/SEPHO_900_280_institutional_banner_20210927_V2.jpg",
        reviews = reviewProduct.reviews,
        reviewsExpanded = false,
        rating = 4f,
    )

    override fun setUp() {
        super.setUp()
        productsWithAssociatedReviewsUseCase = ProductsWithReviewsAssociatedUseCase(
            testDispatcher,
            mockFetchProductsUseCase,
            mockFetchReviewsUseCase
        )
    }

    @Test
    fun `should return products with associated reviews if Products repository returns it`() = runTest {
        coEvery { mockFetchProductsUseCase.invoke() } returns Result.success(listOf(product))
        coEvery { mockFetchReviewsUseCase.invoke() } returns Result.success(listOf(reviewProduct))

        val expected = listOf(productWithReviews)

        val result = productsWithAssociatedReviewsUseCase()
        assertEquals(expected, result.getOrNull())
    }

    @Test
    fun `should return error if products repository throws exception`() = runTest {
        val exception = IllegalStateException()
        coEvery { mockFetchProductsUseCase.invoke() } throws exception

        val result = productsWithAssociatedReviewsUseCase()
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `should return products with empty reviews if reviews Repository throws exception`() = runTest {
        coEvery { mockFetchProductsUseCase.invoke() } returns Result.success(listOf(product))
        coEvery { mockFetchReviewsUseCase.invoke() } returns Result.failure(IllegalStateException())

        val expected = listOf(productWithReviews.copy(reviews = emptyList(), rating = 0f, numberRating = "0 avis"))

        val result = productsWithAssociatedReviewsUseCase()
        assertEquals(expected, result.getOrNull())
    }
}