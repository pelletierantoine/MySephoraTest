package com.pelletierantoine.mysephoratest.data

import com.pelletierantoine.mysephoratest.data.mappers.BrandMapper
import com.pelletierantoine.mysephoratest.data.mappers.ImageUrlsMapper
import com.pelletierantoine.mysephoratest.data.mappers.ProductMapper
import com.pelletierantoine.mysephoratest.data.mappers.ReviewMapper
import com.pelletierantoine.mysephoratest.data.mappers.ReviewProductMapper
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule: Module
    get() = module {

        // Mappers
        single { BrandMapper() }
        single { ImageUrlsMapper() }
        single { ReviewMapper() }
        single { ReviewProductMapper(get()) }
        single { ProductMapper(get(), get()) }
    }