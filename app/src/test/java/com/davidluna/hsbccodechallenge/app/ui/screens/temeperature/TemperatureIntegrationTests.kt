package com.davidluna.hsbccodechallenge.app.ui.screens.temeperature

import app.cash.turbine.test
import com.davidluna.hsbccodechallenge.app.rules.CoroutineTestRule
import com.davidluna.hsbccodechallenge.mocks.TestViewModelFactory
import com.davidluna.hsbccodechallenge.mocks.temperatureListMock
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TemperatureIntegrationTests {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    private lateinit var factory: TestViewModelFactory

    @Before
    fun setUp() {
        factory = TestViewModelFactory()
    }

    @Test
    fun `given view model is created when init is called then state should be updated with temperatures list`() =
        runTest {
            val viewModel = factory.createTemperatureViewModel()
            val state = TemperatureViewModel.State()
            val expected = temperatureListMock

            viewModel.state.onEach { println(it) }.test {
                Truth.assertThat(awaitItem()).isEqualTo(state)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().temperatures).isEqualTo(expected)
                Truth.assertThat(awaitItem().isLoading).isFalse()
                cancel()
            }
        }

}