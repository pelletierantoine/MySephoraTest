package com.pelletierantoine.mysephoratest.data

import com.pelletierantoine.mysephoratest.data.mappers.BrandMapper
import com.pelletierantoine.mysephoratest.data.mappers.ImageUrlsMapper
import com.pelletierantoine.mysephoratest.data.mappers.ProductMapper
import com.pelletierantoine.mysephoratest.data.models.BrandEntity
import com.pelletierantoine.mysephoratest.data.models.ImageUrlsEntity
import com.pelletierantoine.mysephoratest.data.models.ProductEntity
import com.pelletierantoine.mysephoratest.data.repository.ProductsRepositoryImpl
import com.pelletierantoine.mysephoratest.data.service.ProductsApi
import com.pelletierantoine.mysephoratest.domain.models.Brand
import com.pelletierantoine.mysephoratest.domain.models.ImageUrls
import com.pelletierantoine.mysephoratest.domain.models.Product
import io.mockk.coEvery
import io.mockk.mockk
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.get
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductsRepositoryTest : BaseTest() {

    private val productEntity = ProductEntity(
        productId = 12,
        productName = "Size Up - Mascara Volume Extra Large Immédiat",
        description = "Craquez pour le Mascara Size Up de Sephora Collection : Volume XXL immédiat, cils ultra allongés et recourbés ★ Formule vegan longue tenue ✓",
        price = 140f,
        imageUrls = ImageUrlsEntity(
            small = "https://dev.sephora.fr/on/demandware.static/-/Library-Sites-SephoraV2/default/dw521a3f33/brands/institbanner/SEPHO_900_280_institutional_banner_20210927_V2.jpg",
            large = ""
        ),
        brand = BrandEntity("SEPHO", "SEPHORA_COLLECTION"),
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

    override val module: Module = module {
        single { ImageUrlsMapper() }
        single { BrandMapper() }
        single { ProductMapper(get(), get()) }
    }

    private val mockService: ProductsApi = mockk {
        coEvery { fetchProducts() } returns listOf(productEntity, productEntity.copy(productId = 13))
    }

    private lateinit var repository: ProductsRepositoryImpl

    override fun setUp() {
        super.setUp()
        repository = ProductsRepositoryImpl(mockService, get())
    }

    @Test
    fun `must return products if it is loaded`() {
        coEvery { mockService.fetchProducts() } returns listOf(productEntity, productEntity.copy(productId = 13))

        val expected = listOf(product, product.copy(productId = 13))

        runSuspended(
            { repository.fetchProducts() },
            { assertEquals(expected, it) }
        )
    }

    @Test
    fun `must return error if accountPro can not be loaded`() {
        assertError(
            action = { repository.fetchProducts() },
            mock = { mockService.fetchProducts() }
        )
    }
}