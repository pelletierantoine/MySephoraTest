package com.pelletierantoine.mysephoratest.ui.activities

import android.util.Log
import com.pelletierantoine.mysephoratest.domain.usecases.ProductsWithReviewsAssociatedUseCase
import com.pelletierantoine.mysephoratest.ui.BaseViewModel

class MainViewModel(
    private val fetchProductsWithReviewsAssociatedUseCase: ProductsWithReviewsAssociatedUseCase
) : BaseViewModel<MainState>() {

    init {
        launch {
            fetchProductsWithReviewsAssociatedUseCase.invoke()
                .onSuccess {
                    it.forEach {
                        Log.d("TEST", "Product : $it")
                    }
                }
                .onFailure {
                    Log.d("", "")
                }
        }
    }
}