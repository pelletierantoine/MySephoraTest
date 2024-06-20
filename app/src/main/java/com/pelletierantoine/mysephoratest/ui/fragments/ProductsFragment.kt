package com.pelletierantoine.mysephoratest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pelletierantoine.mysephoratest.R
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import com.pelletierantoine.mysephoratest.domain.models.SortingType
import com.pelletierantoine.mysephoratest.ui.adapters.ProductsAdapter
import com.pelletierantoine.mysephoratest.ui.extensions.gone
import com.pelletierantoine.mysephoratest.ui.extensions.hideKeyBoard
import com.pelletierantoine.mysephoratest.ui.extensions.invisible
import com.pelletierantoine.mysephoratest.ui.extensions.visible
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.pelletierantoine.mysephoratest.databinding.FragmentProductsBinding as Binding


class ProductsFragment : Fragment() {

    private lateinit var binding: Binding

    private val productsAdapter = ProductsAdapter()

    private val viewModel: ProductsFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        initObservers()
    }

    private fun initViews() {
        with(binding.rvProductsReviews) {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = productsAdapter
        }

        with(binding.tilSearchBar) {
            setEndIconOnClickListener {
                activity?.hideKeyBoard()
                viewModel.onSearchDisplayChanged(binding.tieSearchBar.text.toString())
            }
        }

        initListeners()
    }

    private fun initListeners() {
        binding.tvRetry.setOnClickListener {
            viewModel.loadProducts()
        }
        binding.tvFilterReviews.setOnClickListener {
            showSortingDialog()
        }
    }

    private fun showSortingDialog() {
        context?.let {
            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            builder.setMessage(getString(R.string.dialog_title_sorting_reviews))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.sorting_reviews_asc)) { dialog, id ->
                    viewModel.sortingReviews(SortingType.BEST_TO_WORST)
                    dialog.cancel()
                }
                .setNegativeButton(getString(R.string.sorting_reviews_dsc)) { dialog, id ->
                    viewModel.sortingReviews(SortingType.WORST_TO_BEST)
                    dialog.cancel()
                }
            val alert: AlertDialog = builder.create()
            alert.show()
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

    private fun render(state: ProductsFragmentViewModel.State) {
        when (state) {
            is ProductsFragmentViewModel.State.Content -> {
                showHideLoader(isLoading = state.isLoading)
                updateProducts(productsWithReviews = state.productsWithReviews)
                handleError(state.isError)
            }

            ProductsFragmentViewModel.State.Empty -> Unit
        }
    }

    private fun showHideLoader(isLoading: Boolean) {
        with(binding.progressBar) {
            if (isLoading) visible() else gone()
        }
    }

    private fun updateProducts(productsWithReviews: List<ProductWithReviews>) {
        if (productsWithReviews.isNotEmpty()) {
            binding.tilSearchBar.visible()
            binding.tvFilterReviews.visible()
            productsAdapter.submitList(productsWithReviews)
        } else {
            handleError(true)
        }
    }

    private fun handleError(isError: Boolean) {
        with(binding) {
            if (isError) {
                tvFilterReviews.gone()
                tilSearchBar.invisible()
                lottieError.visible()
                tvRetry.visible()
            } else {
                lottieError.gone()
                tvRetry.gone()
            }
        }
    }
}