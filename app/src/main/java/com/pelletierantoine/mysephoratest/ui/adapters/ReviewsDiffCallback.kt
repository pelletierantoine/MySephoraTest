package com.pelletierantoine.mysephoratest.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.pelletierantoine.mysephoratest.domain.models.Review

class ReviewsDiffCallback : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(
        oldItem: Review,
        newItem: Review
    ) = oldItem === newItem

    override fun areContentsTheSame(
        oldItem: Review,
        newItem: Review
    ) = oldItem == newItem
}