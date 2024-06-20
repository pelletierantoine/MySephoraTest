package com.pelletierantoine.mysephoratest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pelletierantoine.mysephoratest.R
import com.pelletierantoine.mysephoratest.databinding.ItemReviewBinding
import com.pelletierantoine.mysephoratest.domain.models.Review
import com.pelletierantoine.mysephoratest.ui.extensions.gone
import com.pelletierantoine.mysephoratest.ui.extensions.visible

class ReviewsAdapter : ListAdapter<Review, ReviewsAdapter.ReviewsViewHolder>(ReviewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ReviewsViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Review) {
            with(binding) {
                with(ratingBarReview) {
                    if (item.showStars) visible() else gone()
                    rating = item.rating
                }
                tvNameReview.text = item.name.ifEmpty {
                    binding.root.context.getString(R.string.anonyme)
                }
                tvDescriptionReview.text = item.text
            }
        }
    }
}