package com.pelletierantoine.mysephoratest.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews

class ProductsDiffCallback : DiffUtil.ItemCallback<ProductWithReviews>() {
    override fun areItemsTheSame(
        oldItem: ProductWithReviews,
        newItem: ProductWithReviews
    ) = oldItem === newItem

    override fun areContentsTheSame(
        oldItem: ProductWithReviews,
        newItem: ProductWithReviews
    ) = oldItem == newItem

}