package com.pelletierantoine.mysephoratest.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.pelletierantoine.mysephoratest.R
import com.pelletierantoine.mysephoratest.databinding.ItemProductBinding
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import com.pelletierantoine.mysephoratest.domain.models.Review
import com.pelletierantoine.mysephoratest.ui.extensions.gone
import com.pelletierantoine.mysephoratest.ui.extensions.visible

class ProductsAdapter() : ListAdapter<ProductWithReviews, ProductsAdapter.ProductsViewHolder>(ProductsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductsViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var reviewsAdapter: ReviewsAdapter

        fun bind(item: ProductWithReviews) {

            initReviews(item.reviews, item.reviewsExpanded)

            with(binding) {
                tvProductReviews.text = item.numberRating
                tvProductName.text = item.productName
                tvProductPrice.text = item.priceFormatted

                if (item.brand.isEmpty()) {
                    tvProductBrand.gone()
                } else {
                    tvProductBrand.visible()
                    tvProductBrand.text = item.brand
                }

                ivArrowReviews.setImageResource(if (item.reviewsExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)

                pbImageProduct.visible()
                Glide.with(this.root.context)
                    .load(item.imageUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            pbImageProduct.gone()
                            ivProduct.gone()
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            pbImageProduct.gone()
                            ivProduct.visible()
                            return false
                        }
                    })
                    .into(ivProduct)

                ratingBar.rating = item.rating

                flArrowReviews.setOnClickListener {
                    item.reviewsExpanded = !item.reviewsExpanded
                    notifyItemChanged(layoutPosition)
                }
            }
        }

        private fun initReviews(reviews: List<Review>, reviewsExpanded: Boolean) {
            val reviewsAdapter = ReviewsAdapter()
            with(binding.rvReviews) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = reviewsAdapter

                if (reviewsExpanded) visible() else gone()
            }
            reviewsAdapter.submitList(reviews)
        }
    }
}