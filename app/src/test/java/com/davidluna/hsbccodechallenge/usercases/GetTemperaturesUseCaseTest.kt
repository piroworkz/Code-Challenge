package com.davidluna.hsbccodechallenge.usercases

import arrow.core.Either
import com.davidluna.hsbccodechallenge.data.repositories.TemperatureRepository
import com.davidluna.hsbccodechallenge.domain.AppError
import com.davidluna.hsbccodechallenge.mocks.temperatureListMock
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetTemperaturesUseCaseTest {

    @Mock
    private lateinit var repository: TemperatureRepository

    private val useCase by lazy { GetTemperaturesUseCase(repository) }


    @Test
    fun `on success should return a data list on the right side of either result`() = runTest {
        val expected = Either.Right(temperatureListMock)
        whenever(repository.getTemperature()).thenReturn(expected)
        val actual = useCase.invoke()
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `on IO failure should return an IOError on the left side of either result`() = runTest {
        val expected = Either.Left(AppError.IoError("error"))
        whenever(repository.getTemperature()).thenReturn(expected)
        val actual = useCase.invoke()
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `on unknown failure should return an UnknownError on the left side of either result`() =
        runTest {
            val expected = Either.Left(AppError.UnknownError)
            whenever(repository.getTemperature()).thenReturn(expected)
            val actual = useCase.invoke()
            Truth.assertThat(actual).isEqualTo(expected)
        }

}

