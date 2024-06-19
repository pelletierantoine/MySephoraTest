package com.pelletierantoine.mysephoratest.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pelletierantoine.mysephoratest.databinding.ActivityMainBinding
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import com.pelletierantoine.mysephoratest.ui.activities.MainViewModel
import com.pelletierantoine.mysephoratest.ui.adapters.ProductsAdapter
import com.pelletierantoine.mysephoratest.ui.extensions.gone
import com.pelletierantoine.mysephoratest.ui.extensions.visible
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.pelletierantoine.mysephoratest.databinding.ActivityMainBinding as Binding
import com.pelletierantoine.mysephoratest.ui.activities.MainViewModel as ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productsAdapter: ProductsAdapter

    private val viewModel: ViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        initObservers()
    }

    private fun initViews() {
        productsAdapter = ProductsAdapter(mutableListOf())
        with(binding.rvProductsReviews) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = productsAdapter
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.stateFlow.collect {
                    render(it)
                }
            }
        }
    }

    private fun render(state: ViewModel.State) {
        when (state) {
            is MainViewModel.State.Content -> {
                showHideLoader(isLoading = state.isLoading)
                updateProducts(productsWithReviews = state.productsWithReviews)
            }

            MainViewModel.State.Empty -> {

            }
        }
    }

    private fun showHideLoader(isLoading: Boolean) {
        with(binding.progressBar) {
            if (isLoading) visible() else gone()
        }
    }

    private fun updateProducts(productsWithReviews: List<ProductWithReviews>) {
        productsAdapter.addItems(items = productsWithReviews, clear = true)
    }
}