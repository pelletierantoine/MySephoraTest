package com.pelletierantoine.mysephoratest.domain.usecases

import com.pelletierantoine.mysephoratest.domain.common.NoParamsSuspendingUseCase
import com.pelletierantoine.mysephoratest.domain.models.Brand
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import com.pelletierantoine.mysephoratest.domain.toFormattedPrice
import kotlinx.coroutines.CoroutineDispatcher

class ProductsWithReviewsAssociatedUseCase(
    override val dispatcher: CoroutineDispatcher,
    private val productsUseCase: FetchProductsUseCase,
    private val reviewsUseCase: FetchReviewsUseCase
) : NoParamsSuspendingUseCase<List<ProductWithReviews>>() {

    override suspend fun run(): Result<List<ProductWithReviews>> = runCatching {
        val products = productsUseCase().getOrThrow()
        val reviews = reviewsUseCase().getOrNull()

        val productWithReviews: List<ProductWithReviews> = products.map { product ->
            val reviewProduct = reviews?.first { it.productId == product.productId }
            val rating = reviewProduct?.let {
                val ratingReviews = it.reviews.filter { it.showStars }
                ratingReviews.sumOf { it.rating.toDouble() } / ratingReviews.size
            }?.toFloat()
            ProductWithReviews(
                productName = product.productName,
                numberRating = "${reviewProduct?.reviews?.size ?: 0} avis",
                brand = when (product.brand) {
                    Brand.SEPHORA,
                    Brand.CHANNEL -> product.brand.entireName

                    Brand.UNKNOWN -> ""
                },
                description = product.description,
                priceFormatted = product.price.toFormattedPrice(),
                imageUrl = product.imageUrls.small,
                reviews = reviewProduct?.reviews?.sortedByDescending { it.rating } ?: emptyList(),
                reviewsExpanded = false,
                rating = rating ?: 0f
            )
        }

        return Result.success(productWithReviews)
    }
}