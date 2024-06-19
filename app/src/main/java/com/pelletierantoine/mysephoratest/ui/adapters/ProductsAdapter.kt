package com.pelletierantoine.mysephoratest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.pelletierantoine.mysephoratest.databinding.ItemProductBinding
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import com.pelletierantoine.mysephoratest.ui.extensions.toFormattedPrice

class ProductsAdapter(productWithReviews: MutableList<ProductWithReviews>) : GenericAdapter<ProductWithReviews, ItemProductBinding>(productWithReviews) {
    override fun onBindData(model: ProductWithReviews, position: Int, bindingAdapter: ItemProductBinding) {
        with(bindingAdapter) {
            tvProductReviews.text = "${model.reviews.size} avis"
            tvProductName.text = model.productName
            tvProductPrice.text = model.price.toFormattedPrice()
            tvProductBrand.text = model.brand.entireName
            Glide.with(bindingAdapter.root.context)
                .load(model.imageUrls.small)
                .into(bindingAdapter.ivProduct)
        }
    }

    override fun onItemClick(model: ProductWithReviews, position: Int, bindingAdapter: ItemProductBinding) {
        val updateItem = model.copy(reviewsExpanded = true)
        updateItem(updateItem, position)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemProductBinding {
        return ItemProductBinding.inflate(LayoutInflater.from(parent.context))
    }
}