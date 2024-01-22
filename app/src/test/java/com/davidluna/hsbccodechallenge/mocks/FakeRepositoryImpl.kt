package com.davidluna.hsbccodechallenge.mocks

import arrow.core.Either
import com.davidluna.hsbccodechallenge.app.utils.suspendTryCatch
import com.davidluna.hsbccodechallenge.data.repositories.TemperatureRepository
import com.davidluna.hsbccodechallenge.domain.AppError
import com.davidluna.hsbccodechallenge.domain.Data

class FakeRepositoryImpl : TemperatureRepository {
    override suspend fun getTemperature(): Either<AppError, List<Data>> {
        return suspendTryCatch {
            temperatureListMock
        }
    }
}