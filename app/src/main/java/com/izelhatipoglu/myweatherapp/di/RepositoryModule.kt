package com.izelhatipoglu.myweatherapp.di

import com.izelhatipoglu.myweatherapp.data.api.WeatherApiService
import com.izelhatipoglu.myweatherapp.data.remote.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherApiService: WeatherApiService
    ) = WeatherRepository(weatherApiService = weatherApiService)
}