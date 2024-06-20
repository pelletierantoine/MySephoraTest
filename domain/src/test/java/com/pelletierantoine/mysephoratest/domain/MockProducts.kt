package com.pelletierantoine.mysephoratest.domain

import com.pelletierantoine.mysephoratest.domain.models.Brand
import com.pelletierantoine.mysephoratest.domain.models.ImageUrls
import com.pelletierantoine.mysephoratest.domain.models.Product
import com.pelletierantoine.mysephoratest.domain.models.ProductWithReviews
import com.pelletierantoine.mysephoratest.domain.models.Review

private val product = Product(
    productId = 1,
    productName = "Size Up - Mascara Volume Extra Large Immédiat",
    description = "Craquez pour le Mascara Size Up de Sephora Collection : Volume XXL immédiat, cils ultra allongés et recourbés ★ Formule vegan longue tenue ✓",
    price = 140f,
    imageUrls = ImageUrls(
        small = "https://dev.sephora.fr/on/demandware.static/-/Library-Sites-SephoraV2/default/dw521a3f33/brands/institbanner/SEPHO_900_280_institutional_banner_20210927_V2.jpg",
        large = ""
    ),
    brand = Brand.SEPHORA,
    isProductSet = false,
    isSpecialBrand = false
)

val review = Review(name = "Antoine PELLETIER", text = "Excellent produit !", rating = 4f, showStars = true)

val productWithReviews = ProductWithReviews(
    productName = product.productName,
    description = product.description,
    numberRating = "1 avis",
    brand = Brand.SEPHORA.entireName,
    priceFormatted = product.price.toFormattedPrice(),
    imageUrl = product.imageUrls.small,
    reviews = listOf(review),
    reviewsExpanded = false,
    rating = 4f,
)