package com.davidluna.hsbccodechallenge.mocks

import com.davidluna.hsbccodechallenge.app.ui.screens.temeperature.TemperatureViewModel
import com.davidluna.hsbccodechallenge.data.repositories.TemperatureRepository
import com.davidluna.hsbccodechallenge.usercases.GetTemperaturesUseCase

class TestViewModelFactory {
    private val repository: TemperatureRepository = FakeRepositoryImpl()
    private val useCase = GetTemperaturesUseCase(repository)
    fun createTemperatureViewModel() = TemperatureViewModel(useCase)
}