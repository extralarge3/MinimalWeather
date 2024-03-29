package com.example.minimalweather.di

import com.example.minimalweather.network.WeatherService
import com.example.minimalweather.persistence.CurrentWeatherDao
import com.example.minimalweather.persistence.LocationDao
import com.example.minimalweather.ui.details.DetailsRepository
import com.example.minimalweather.ui.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
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
        locationDao: LocationDao
    ): DetailsRepository {
        return DetailsRepository(weatherService, currentWeatherDao, locationDao)
    }

}