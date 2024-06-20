package com.pelletierantoine.mysephoratest.domain.usecases

import com.pelletierantoine.mysephoratest.domain.common.SynchronousUseCase
import com.pelletierantoine.mysephoratest.domain.common.UseCaseParams
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import com.pelletierantoine.mysephoratest.domain.models.SortingType

class FilterReviewsBySortingTypeUseCase : SynchronousUseCase<FilterReviewsBySortingTypeUseCase.Params, List<ProductWithReviews>>() {

    override fun create(params: Params): List<ProductWithReviews> {
        return params.productsReviews.map {
            val reviews = when (params.sortingType) {
                SortingType.WORST_TO_BEST -> it.reviews.sortedByDescending { it.rating }
                SortingType.BEST_TO_WORST -> it.reviews.sortedBy { it.rating }
            }
            it.copy(reviews = reviews)
        }
    }

    data class Params(
        val sortingType: SortingType,
        val productsReviews: List<ProductWithReviews>
    ) : UseCaseParams
}