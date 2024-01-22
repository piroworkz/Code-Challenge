package com.davidluna.hsbccodechallenge.app.di

import android.app.Application
import android.content.res.AssetManager
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object AppTestModule {
    @Singleton
    @Provides
    fun provideAssetsManager(application: Application): AssetManager =
        application.assets
}