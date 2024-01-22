package com.davidluna.hsbccodechallenge.usercases

import arrow.core.Either
import com.davidluna.hsbccodechallenge.data.repositories.TemperatureRepository
import com.davidluna.hsbccodechallenge.domain.AppError
import com.davidluna.hsbccodechallenge.domain.Data
import javax.inject.Inject

class GetTemperaturesUseCase @Inject constructor(private val repository: TemperatureRepository) {

    suspend operator fun invoke(): Either<AppError, List<Data>> = repository.getTemperature()

}