package com.example.minimalweather.ui.details

import com.example.minimalweather.Model.CurrentWeather
import com.example.minimalweather.Model.ForecastWeather
import com.example.minimalweather.Model.Location
import com.example.minimalweather.Network.WeatherService
import com.example.minimalweather.Persistence.CurrentWeatherDao
import com.example.minimalweather.Persistence.LocationDao
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val currentWeatherDao: CurrentWeatherDao,
) {
    fun getDetailedWeather(loc: Location): CurrentWeather {
        return TODO("Provide the return value")
    }

    fun getForecast(loc: Location): ForecastWeather{
        return TODO("Provide the return value")
    }
}