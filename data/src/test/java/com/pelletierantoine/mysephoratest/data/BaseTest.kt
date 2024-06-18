package com.pelletierantoine.mysephoratest.data

import io.mockk.MockKMatcherScope
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.fail

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

    open fun <T> runSuspended(action: suspend () -> T, expectation: (result: T) -> Unit) {
        runTest(StandardTestDispatcher()) {
            val result = action()
            advanceUntilIdle()
            expectation(result)
        }
    }

    open fun <T> assertError(
        action: suspend () -> T,
        mock: suspend MockKMatcherScope.() -> T,
        expectation: ((Throwable) -> Unit)? = null,
        exception: Throwable = HttpException(Response.success(""))
    ) {
        coEvery { mock() } throws exception
        try {
            runSuspended(
                { action() },
                { fail() }
            )
        } catch (e: Throwable) {
            expectation?.invoke(e)
        }
    }
}