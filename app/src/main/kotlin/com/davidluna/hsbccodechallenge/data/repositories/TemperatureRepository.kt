package com.davidluna.hsbccodechallenge.data.repositories

import arrow.core.Either
import com.davidluna.hsbccodechallenge.domain.AppError
import com.davidluna.hsbccodechallenge.domain.Data

interface TemperatureRepository {
    suspend fun getTemperature(): Either<AppError, List<Data>>
}