package com.pelletierantoine.mysephoratest.data.mappers

import com.pelletierantoine.mysephoratest.data.BaseTest
import com.pelletierantoine.mysephoratest.data.models.BrandEntity
import com.pelletierantoine.mysephoratest.data.models.ImageUrlsEntity
import com.pelletierantoine.mysephoratest.data.models.ProductEntity
import com.pelletierantoine.mysephoratest.domain.models.Brand
import com.pelletierantoine.mysephoratest.domain.models.ImageUrls
import com.pelletierantoine.mysephoratest.domain.models.Product
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.get
import kotlin.test.assertEquals

class ProductsMapperTest : BaseTest() {

    override val module: Module = module {
        single { ImageUrlsMapper() }
        single { BrandMapper() }
    }

    private val mapper: ProductMapper by lazy { ProductMapper(get(), get()) }

    private val productEntity = ProductEntity(
        productId = 12,
        productName = "Size Up - Mascara Volume Extra Large Immédiat",
        description = "Craquez pour le Mascara Size Up de Sephora Collection : Volume XXL immédiat, cils ultra allongés et recourbés ★ Formule vegan longue tenue ✓",
        price = 140f,
        imageUrls = ImageUrlsEntity(
            small = "https://dev.sephora.fr/on/demandware.static/-/Library-Sites-SephoraV2/default/dw521a3f33/brands/institbanner/SEPHO_900_280_institutional_banner_20210927_V2.jpg",
            large = ""
        ),
        brand = BrandEntity("SEPHO", "SEPHORA COLLECTION"),
        isProductSet = false,
        isSpecialBrand = false
    )

    private val product = Product(
        productId = 12,
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

    @Test
    fun `from entity`() {
        val testObject = mapper.fromEntity(product)
        assertEquals(testObject, productEntity)
    }

    @Test
    fun `to entity`() {
        val testObject = mapper.toEntity(productEntity)
        assertEquals(testObject, product)
    }
}