package com.pelletierantoine.mysephoratest.di

import com.pelletierantoine.mysephoratest.ui.fragments.ProductsFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelsModule: Module
    get() = module {
        viewModel { ProductsFragmentViewModel(get(), get(), get()) }
    }