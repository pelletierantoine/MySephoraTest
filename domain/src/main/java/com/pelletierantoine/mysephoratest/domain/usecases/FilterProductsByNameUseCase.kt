package com.pelletierantoine.mysephoratest.domain.usecases

import com.pelletierantoine.mysephoratest.domain.common.SynchronousUseCase
import com.pelletierantoine.mysephoratest.domain.common.UseCaseParams
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews

class FilterProductsByNameUseCase : SynchronousUseCase<FilterProductsByNameUseCase.Params, List<ProductWithReviews>>() {

    override fun create(params: Params): List<ProductWithReviews> {
        return if (params.productName.isNotEmpty()) {
            params.productsReviews.filter { it.productName.contains(params.productName) }
        } else {
            params.productsReviews
        }
    }

    data class Params(
        val productName: String,
        val productsReviews: List<ProductWithReviews>
    ) : UseCaseParams
}