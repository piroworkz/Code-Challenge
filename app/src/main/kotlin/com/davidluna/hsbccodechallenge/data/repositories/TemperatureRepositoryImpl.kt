package com.davidluna.hsbccodechallenge.data.repositories

import android.content.res.AssetManager
import arrow.core.Either
import com.davidluna.hsbccodechallenge.app.utils.suspendTryCatch
import com.davidluna.hsbccodechallenge.data.utils.toDomain
import com.davidluna.hsbccodechallenge.domain.AppError
import com.davidluna.hsbccodechallenge.domain.Data
import javax.inject.Inject

class TemperatureRepositoryImpl @Inject constructor(private val assets: AssetManager) :
    TemperatureRepository {

    override suspend fun getTemperature(): Either<AppError, List<Data>> = suspendTryCatch {
        assets.open("temperatures.json").bufferedReader().use { it.readText() }.toDomain()
    }

}
