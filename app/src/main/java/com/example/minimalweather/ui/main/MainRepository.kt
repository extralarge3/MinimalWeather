package com.example.minimalweather.ui.main

import com.example.minimalweather.Model.CurrentWeather
import com.example.minimalweather.Model.Location
import com.example.minimalweather.Model.Network.WeatherDataNetwork
import com.example.minimalweather.Network.WeatherService
import com.example.minimalweather.Persistence.CurrentWeatherDao
import com.example.minimalweather.Persistence.LocationDao
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val locationDao: LocationDao,
    private val currentWeatherDao: CurrentWeatherDao
) {
    fun loadCurrentWeatherForAll(): List<CurrentWeather>{
        return TODO("Provide the return value")
    }

    fun loadCurrentWeatherForLocation(loc: Location): CurrentWeather{
        return TODO()
    }

    fun addLocation(loc: Location){}

    fun removeLocation(loc: Location){}

    suspend fun networktest(loc: String) : ApiResponse<WeatherDataNetwork> {
        return  weatherService.fetchCurrentWeather(loc)
    }


}