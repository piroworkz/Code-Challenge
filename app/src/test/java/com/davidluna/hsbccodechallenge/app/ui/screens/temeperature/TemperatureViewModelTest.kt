package com.davidluna.hsbccodechallenge.app.ui.screens.temeperature

import app.cash.turbine.test
import arrow.core.Either
import com.davidluna.hsbccodechallenge.app.rules.CoroutineTestRule
import com.davidluna.hsbccodechallenge.domain.AppError
import com.davidluna.hsbccodechallenge.domain.Data
import com.davidluna.hsbccodechallenge.mocks.temperatureListMock
import com.davidluna.hsbccodechallenge.usercases.GetTemperaturesUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class TemperatureViewModelTest {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var getTemperaturesUseCase: GetTemperaturesUseCase

    private val state = TemperatureViewModel.State()
    private val temperatures = temperatureListMock

    @Test
    fun `on viewModel init downloads a list a of temperatures`() = runTest {
        whenever(getTemperaturesUseCase()).thenReturn(Either.Right(temperatures))
        val viewModel = TemperatureViewModel(getTemperaturesUseCase)
        val expected = state.copy(temperatures = temperatures)

        viewModel.state.onEach { println(it) }.test {
            Truth.assertThat(awaitItem()).isEqualTo(state)
            Truth.assertThat(awaitItem().isLoading).isTrue()
            Truth.assertThat(awaitItem().temperatures).isEqualTo(expected.temperatures)
            Truth.assertThat(awaitItem().isLoading).isFalse()
            cancel()
        }
    }

    @Test
    fun `on getRandomData selects a new random temperature`() = runTest {
        whenever(getTemperaturesUseCase()).thenReturn(Either.Right(temperatures))
        val viewModel = TemperatureViewModel(getTemperaturesUseCase)

        viewModel.state.onEach { println(it) }.test {
            Truth.assertThat(awaitItem()).isEqualTo(state)
            Truth.assertThat(awaitItem().isLoading).isTrue()
            val firstRandom = awaitItem().randomData
            Truth.assertThat(awaitItem().isLoading).isFalse()
            viewModel.onEvent(Event.RandomTemp)
            Truth.assertThat(awaitItem().randomData).isNotEqualTo(firstRandom)
            cancel()
        }
    }

    @Test
    fun `on getRandomData throws a NoMoreData error when there are no more temperatures to select`() =
        runTest {
            whenever(getTemperaturesUseCase()).thenReturn(Either.Right(temperatures.take(1)))
            val viewModel = TemperatureViewModel(getTemperaturesUseCase)
            val expected = AppError.NoMoreData

            viewModel.state.onEach { println(it) }.test {
                Truth.assertThat(awaitItem()).isEqualTo(state)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                awaitItem()
                Truth.assertThat(awaitItem().isLoading).isFalse()
                viewModel.onEvent(Event.RandomTemp)
                Truth.assertThat(awaitItem().error).isEqualTo(expected)
                cancel()
            }
        }

    @Test
    fun `on resetError resets the error`() = runTest {
        whenever(getTemperaturesUseCase()).thenReturn(Either.Right(temperatures.take(1)))
        val viewModel = TemperatureViewModel(getTemperaturesUseCase)

        viewModel.state.onEach { println(it) }.test {
            Truth.assertThat(awaitItem()).isEqualTo(state)
            Truth.assertThat(awaitItem().isLoading).isTrue()
            awaitItem()
            Truth.assertThat(awaitItem().isLoading).isFalse()
            viewModel.onEvent(Event.RandomTemp)
            Truth.assertThat(awaitItem().error).isEqualTo(AppError.NoMoreData)
            viewModel.onEvent(Event.ResetError)
            Truth.assertThat(awaitItem().error).isNull()
            cancel()
        }
    }

    @Test
    fun `on resetData clears shownData`() = runTest {
        whenever(getTemperaturesUseCase()).thenReturn(Either.Right(temperatures.take(1)))
        val viewModel = TemperatureViewModel(getTemperaturesUseCase)
        val expected = mutableListOf<Data>()

        viewModel.state.onEach { println(it) }.test {
            Truth.assertThat(awaitItem()).isEqualTo(state)
            Truth.assertThat(awaitItem().isLoading).isTrue()
            awaitItem()
            Truth.assertThat(awaitItem().isLoading).isFalse()
            viewModel.onEvent(Event.RandomTemp)
            Truth.assertThat(awaitItem().error).isEqualTo(AppError.NoMoreData)
            viewModel.onEvent(Event.ResetError)
            Truth.assertThat(awaitItem().error).isNull()
            viewModel.onEvent(Event.ResetData)
            Truth.assertThat(awaitItem().shownData).isEqualTo(expected)
            cancel()
        }
    }
}