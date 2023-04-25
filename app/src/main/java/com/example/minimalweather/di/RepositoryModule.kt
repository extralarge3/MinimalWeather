package com.example.minimalweather.di

import com.example.minimalweather.Network.WeatherService
import com.example.minimalweather.Persistence.CurrentWeatherDao
import com.example.minimalweather.Persistence.LocationDao
import com.example.minimalweather.ui.details.DetailsRepository
import com.example.minimalweather.ui.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        weatherService: WeatherService,
        weatherDao: CurrentWeatherDao,
        locationDao: LocationDao
    ): MainRepository {
        return MainRepository(weatherService, locationDao, weatherDao)
    }

    @Provides
    @ViewModelScoped
    fun provideDetailsRepository(
        weatherService: WeatherService,
        currentWeatherDao: CurrentWeatherDao,
    ): DetailsRepository {
        return DetailsRepository(weatherService, currentWeatherDao)
    }

}