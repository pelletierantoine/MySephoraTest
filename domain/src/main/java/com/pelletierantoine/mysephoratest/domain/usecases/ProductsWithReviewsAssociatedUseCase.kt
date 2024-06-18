package com.pelletierantoine.mysephoratest.domain.usecases

import com.pelletierantoine.mysephoratest.domain.common.NoParamsSuspendingUseCase
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import kotlinx.coroutines.CoroutineDispatcher

class ProductsWithReviewsAssociatedUseCase(
    override val dispatcher: CoroutineDispatcher,
    private val productsUseCase: FetchProductsUseCase,
    private val reviewsUseCase: FetchReviewsUseCase
) : NoParamsSuspendingUseCase<List<ProductWithReviews>>() {

    override suspend fun run(): Result<List<ProductWithReviews>> = runCatching {
        val productWithReviews = mutableListOf<ProductWithReviews>()
        val products = productsUseCase.invoke().getOrThrow()
        val reviews = reviewsUseCase.invoke().getOrNull()
        products.forEach { product ->
            val reviewProduct = reviews?.first { it.productId == product.productId }
            productWithReviews.add(
                ProductWithReviews(
                    productName = product.productName,
                    description = product.description,
                    price = product.price,
                    imageUrls = product.imageUrls,
                    brand = product.brand,
                    reviews = reviewProduct?.reviews ?: emptyList()
                )
            )
        }
        return Result.success(productWithReviews)
    }
}