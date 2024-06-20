package com.pelletierantoine.mysephoratest.domain.di

import com.pelletierantoine.mysephoratest.domain.usecases.FetchProductsUseCase
import com.pelletierantoine.mysephoratest.domain.usecases.FetchReviewsUseCase
import com.pelletierantoine.mysephoratest.domain.usecases.FilterProductsByNameUseCase
import com.pelletierantoine.mysephoratest.domain.usecases.FilterReviewsBySortingTypeUseCase
import com.pelletierantoine.mysephoratest.domain.usecases.ProductsWithReviewsAssociatedUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

val domainModule: Module
    get() = module {
        // Use cases
        single { Dispatchers.IO }

        factory { FetchProductsUseCase(get(), get()) }
        factory { FetchReviewsUseCase(get(), get()) }
        factory { ProductsWithReviewsAssociatedUseCase(get(), get(), get()) }
        factory { FilterProductsByNameUseCase() }
        factory { FilterReviewsBySortingTypeUseCase() }
    }