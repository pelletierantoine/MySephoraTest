package com.pelletierantoine.mysephoratest.data

import com.pelletierantoine.mysephoratest.data.mappers.BrandMapper
import com.pelletierantoine.mysephoratest.data.mappers.ImageUrlsMapper
import com.pelletierantoine.mysephoratest.data.mappers.ProductMapper
import com.pelletierantoine.mysephoratest.data.mappers.ReviewMapper
import com.pelletierantoine.mysephoratest.data.mappers.ReviewProductMapper
import com.pelletierantoine.mysephoratest.data.repository.ProductsRepositoryImpl
import com.pelletierantoine.mysephoratest.data.repository.ReviewsRepositoryImpl
import com.pelletierantoine.mysephoratest.data.service.ProductsApi
import com.pelletierantoine.mysephoratest.data.service.ReviewsApi
import com.pelletierantoine.mysephoratest.domain.repository.ProductsRepository
import com.pelletierantoine.mysephoratest.domain.repository.ReviewsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

val dataModule: Module
    get() = module {
        // Mappers
        single { BrandMapper() }
        single { ImageUrlsMapper() }
        single { ReviewMapper() }
        single { ReviewProductMapper(get()) }
        single { ProductMapper(get(), get()) }

        // Retrofit
        singleOf(::createLoggingInterceptor)
        single { createOkHttpClient() }
        single { createRetrofit(get()) }
        single { get<Retrofit>().create(ProductsApi::class.java) }
        single { get<Retrofit>().create(ReviewsApi::class.java) }

        // Repositories
        single<ProductsRepository> { ProductsRepositoryImpl(get(), get()) }
        single<ReviewsRepository> { ReviewsRepositoryImpl(get(), get()) }
    }

private fun createLoggingInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

private fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(60L, TimeUnit.SECONDS)
    .readTimeout(60L, TimeUnit.SECONDS)
    .addInterceptor(KoinJavaComponent.get<HttpLoggingInterceptor>(HttpLoggingInterceptor::class.java))
    .cookieJar(JavaNetCookieJar(CookieManager().apply { setCookiePolicy(CookiePolicy.ACCEPT_ALL) }))
    .build()

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    return Retrofit.Builder()
        .baseUrl("https://sephoraandroid.github.io/testProject/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

