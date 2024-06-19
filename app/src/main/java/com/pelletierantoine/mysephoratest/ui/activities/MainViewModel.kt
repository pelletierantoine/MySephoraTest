package com.pelletierantoine.mysephoratest.ui.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import com.pelletierantoine.mysephoratest.domain.usecases.ProductsWithReviewsAssociatedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val fetchProductsWithReviewsAssociatedUseCase: ProductsWithReviewsAssociatedUseCase
) : ViewModel() {

    sealed class State {
        data object Empty : State()
        data class Content(
            val productsWithReviews: List<ProductWithReviews>,
            val isLoading: Boolean
        ) : State()
    }

    private var products = mutableListOf<ProductWithReviews>()

    private val _isLoading = MutableStateFlow(false)
    private val _productsState = MutableStateFlow<List<ProductWithReviews>>(emptyList())

    val stateFlow: StateFlow<State> = combine(
        _isLoading,
        _productsState
    )
    { isLoading, products ->
        State.Content(
            productsWithReviews = products,
            isLoading = isLoading
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), State.Empty)

    init {
        viewModelScope.launch {
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
                }
        }
    }

    fun onSearchDisplayChanged(searchText: String) = viewModelScope.launch {
        if (searchText.isNotEmpty()) {
            _productsState.emit(products.filter { it.productName.contains(searchText) })
        }
    }
}