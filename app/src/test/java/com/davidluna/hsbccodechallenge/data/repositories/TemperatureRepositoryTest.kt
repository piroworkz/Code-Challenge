package com.davidluna.hsbccodechallenge.data.repositories

import android.content.res.AssetManager
import arrow.core.Either
import com.davidluna.hsbccodechallenge.domain.AppError
import com.davidluna.hsbccodechallenge.mocks.temperatureListMock
import com.davidluna.hsbccodechallenge.mocks.tempFile
import com.google.common.truth.Truth
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import java.io.FileNotFoundException

@RunWith(MockitoJUnitRunner::class)
class TemperatureRepositoryTest {

    @Mock
    private lateinit var assets: AssetManager

    private val repository by lazy { TemperatureRepositoryImpl(assets) }

    @Test
    fun `on success should return a data list on the right side of either result`() = runTest {
        val expected = Either.Right(temperatureListMock)
        val json = Gson().toJson(tempFile)
        whenever(assets.open("temperatures.json")).thenReturn(json.byteInputStream())
        val actual = repository.getTemperature()
        Truth.assertThat(actual).isEqualTo(expected)
    }


    @Test
    fun `on failure should return UnknownError on the left side of either result`() = runTest {
        val expected =
            Either.Left(AppError.UnknownError)
        whenever(assets.open("temperatures.json")).thenReturn(null)
        val actual = repository.getTemperature()
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `on failure should return IoError on the left side of either result`() = runTest {
        val expected = Either.Left(AppError.IoError("File not found. Please contact support."))
        whenever(assets.open("temperatures.json")).thenThrow(FileNotFoundException())
        val actual = repository.getTemperature()
        Truth.assertThat(actual).isEqualTo(expected)
    }

}