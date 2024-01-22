package com.davidluna.hsbccodechallenge.app.di

import com.davidluna.hsbccodechallenge.data.repositories.TemperatureRepositoryImpl
import com.davidluna.hsbccodechallenge.data.repositories.TemperatureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindTemperatureDataSource(localTemperatureDataSource: TemperatureRepositoryImpl): TemperatureRepository

}