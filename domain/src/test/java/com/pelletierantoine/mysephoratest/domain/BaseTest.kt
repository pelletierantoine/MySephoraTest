package com.pelletierantoine.mysephoratest.domain

import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseTest : KoinTest {

    protected val testDispatcher = StandardTestDispatcher()
    protected open val module = module {
        // by default is empty, instances of all classes suppose to be created manually
        // override and define instances of used classes, if they need to be injected in the test
    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(module)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { mockk() }

    @Before
    open fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }
}