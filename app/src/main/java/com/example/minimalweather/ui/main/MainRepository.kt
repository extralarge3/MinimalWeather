package com.example.minimalweather.ui.main

import com.example.minimalweather.Model.CurrentWeather
import com.example.minimalweather.Model.Location
import com.example.minimalweather.Network.WeatherService
import com.example.minimalweather.Persistence.CurrentWeatherDao
import com.example.minimalweather.Persistence.LocationDao

class MainRepository(
    private val weatherService: WeatherService,
    private val locationDao: LocationDao,
    private val currentWeatherDao: CurrentWeatherDao
) {
    fun loadCurrentWeatherForAll(): List<CurrentWeather>{
        return TODO("Provide the return value")
    }

    fun loadCurrentWeatherForLocation(): CurrentWeather{
        return TODO("Provide the return value")
    }

    fun addLocation(loc: Location){}

    fun removeLocation(loc: Location){}
}