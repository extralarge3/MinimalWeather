package com.example.minimalweather.di

import com.example.minimalweather.Network.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideWeatherService(): WeatherService {
        return TODO("Provide the return value")
    }
}