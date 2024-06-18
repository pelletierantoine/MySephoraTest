package com.pelletierantoine.mysephoratest.domain.usecases

import com.pelletierantoine.mysephoratest.domain.common.NoParamsSuspendingUseCase
import com.pelletierantoine.mysephoratest.domain.models.ReviewProduct
import com.pelletierantoine.mysephoratest.domain.repository.ReviewsRepository
import kotlinx.coroutines.CoroutineDispatcher

class FetchReviewsUseCase(
    override val dispatcher: CoroutineDispatcher,
    private val repository: ReviewsRepository
) : NoParamsSuspendingUseCase<List<ReviewProduct>>() {

    override suspend fun run(): Result<List<ReviewProduct>> = runCatching {
        repository.fetchReviews()
    }
}