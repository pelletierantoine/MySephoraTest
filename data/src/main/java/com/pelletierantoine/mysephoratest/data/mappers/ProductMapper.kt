package com.pelletierantoine.mysephoratest.data.mappers

import com.pelletierantoine.mysephoratest.data.models.ProductEntity
import com.pelletierantoine.mysephoratest.domain.models.Product

internal class ProductMapper(
    private val imagesUrlsMapper: ImageUrlsMapper,
    private val brandMapper: BrandMapper
) : Mapper<ProductEntity, Product> {
    override fun fromEntity(entity: Product): ProductEntity {
        return ProductEntity(
            productId = entity.productId,
            productName = entity.productName,
            description = entity.description,
            price = entity.price,
            imageUrls = entity.imageUrls.let(imagesUrlsMapper::fromEntity),
            brand = entity.brand.let(brandMapper::fromEntity),
            isProductSet = entity.isProductSet,
            isSpecialBrand = entity.isSpecialBrand
        )
    }

    override fun toEntity(data: ProductEntity): Product {
        return Product(
            productId = data.productId,
            productName = data.productName,
            description = data.description,
            price = data.price,
            imageUrls = data.imageUrls.let(imagesUrlsMapper::toEntity),
            brand = data.brand.let(brandMapper::toEntity),
            isProductSet = data.isProductSet,
            isSpecialBrand = data.isSpecialBrand
        )
    }
}