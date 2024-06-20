package com.pelletierantoine.mysephoratest.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import com.pelletierantoine.mysephoratest.domain.models.SortingType
import com.pelletierantoine.mysephoratest.domain.usecases.FilterProductsByNameUseCase
import com.pelletierantoine.mysephoratest.domain.usecases.FilterReviewsBySortingTypeUseCase
import com.pelletierantoine.mysephoratest.domain.usecases.ProductsWithReviewsAssociatedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductsFragmentViewModel(
    private val fetchProductsWithReviewsAssociatedUseCase: ProductsWithReviewsAssociatedUseCase,
    private val filterProductsByNameUseCase: FilterProductsByNameUseCase,
    private val filterReviewsBySortingTypeUseCase: FilterReviewsBySortingTypeUseCase
) : ViewModel() {

    sealed class State {
        data object Empty : State()
        data class Content(
            val productsWithReviews: List<ProductWithReviews>,
            val isLoading: Boolean,
            val isError: Boolean,
        ) : State()
    }

    private var products = mutableListOf<ProductWithReviews>()

    private val _isLoading = MutableStateFlow(false)
    private val _isError = MutableStateFlow(false)
    private val _productsState = MutableStateFlow<List<ProductWithReviews>>(emptyList())

    val stateFlow: StateFlow<State> = combine(
        _isLoading,
        _productsState,
        _isError
    )
    { isLoading, products, isError ->
        State.Content(
            productsWithReviews = products,
            isLoading = isLoading,
            isError = isError
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), State.Empty)

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _isError.emit(false)
            _isLoading.emit(true)

            fetchProductsWithReviewsAssociatedUseCase.invoke()
                .onSuccess {
                    _isLoading.emit(false)

                    with(products) {
                        clear()
                        addAll(it)
                    }

                    _productsState.emit(products)
                }
                .onFailure {
                    _isLoading.emit(false)
                    _isError.emit(true)
                }
        }
    }

    fun onSearchDisplayChanged(searchText: String) = viewModelScope.launch {
        _productsState.emit(
            filterProductsByNameUseCase.execute(FilterProductsByNameUseCase.Params(searchText, products))
        )
    }

    fun sortingReviews(sortingType: SortingType) = viewModelScope.launch {
        _productsState.emit(
            filterReviewsBySortingTypeUseCase.execute(FilterReviewsBySortingTypeUseCase.Params(sortingType, products))
        )
    }
}