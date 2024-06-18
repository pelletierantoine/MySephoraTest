package com.pelletierantoine.mysephoratest.domain

import com.pelletierantoine.mysephoratest.domain.models.ReviewProduct
import com.pelletierantoine.mysephoratest.domain.repository.ReviewsRepository
import com.pelletierantoine.mysephoratest.domain.usecases.FetchReviewsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FetchReviewsUseCaseTest : BaseTest() {

    private val mockReviewsRepository: ReviewsRepository = mockk()

    private lateinit var fetchReviewsUseCase: FetchReviewsUseCase

    override fun setUp() {
        super.setUp()
        fetchReviewsUseCase = FetchReviewsUseCase(
            testDispatcher,
            mockReviewsRepository
        )
    }

    @Test
    fun `should return reviews if repository returns it`() = runTest {
        val mockReviews: List<ReviewProduct> = mockk()
        coEvery { mockReviewsRepository.fetchReviews() } returns mockReviews

        val result = fetchReviewsUseCase()
        assertEquals(mockReviews, result.getOrNull())
    }

    @Test
    fun `should return error if repository throws exception`() = runTest {
        val exception = IllegalStateException()
        coEvery { mockReviewsRepository.fetchReviews() } throws exception

        val result = fetchReviewsUseCase()
        assertEquals(exception, result.exceptionOrNull())
    }
}