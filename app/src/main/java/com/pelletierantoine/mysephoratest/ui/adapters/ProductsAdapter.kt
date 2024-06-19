package com.pelletierantoine.mysephoratest.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.pelletierantoine.mysephoratest.databinding.ItemProductBinding
import com.pelletierantoine.mysephoratest.domain.models.Brand
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import com.pelletierantoine.mysephoratest.ui.extensions.gone
import com.pelletierantoine.mysephoratest.ui.extensions.toFormattedPrice
import com.pelletierantoine.mysephoratest.ui.extensions.visible

class ProductsAdapter(productWithReviews: MutableList<ProductWithReviews>) : GenericAdapter<ProductWithReviews, ItemProductBinding>(productWithReviews) {
    override fun onBindData(item: ProductWithReviews, position: Int, bindingAdapter: ItemProductBinding) {
        with(bindingAdapter) {

            tvProductReviews.text = "${item.reviews.size} avis"
            tvProductName.text = item.productName
            tvProductPrice.text = item.price.toFormattedPrice()

            when (item.brand) {
                Brand.SEPHORA,
                Brand.CHANNEL -> {
                    tvProductBrand.visible()
                    tvProductBrand.text = item.brand.entireName
                }

                Brand.UNKNOWN -> tvProductBrand.gone()
            }

            pbImageProduct.visible()

            Glide.with(bindingAdapter.root.context)
                .load(item.imageUrls.small)
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
        }
    }

    override fun onItemClick(item: ProductWithReviews, position: Int, bindingAdapter: ItemProductBinding) {
        val updateItem = item.copy(reviewsExpanded = true)
        updateItem(updateItem, position)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemProductBinding {
        return ItemProductBinding.inflate(inflater, parent, false)
    }
}