package com.example.minimalweather.Network

import com.example.minimalweather.Model.CurrentWeather
import com.example.minimalweather.Model.ForecastWeather
import com.example.minimalweather.Model.Location

interface WeatherService {
    fun fetchCurrentWeather(loc: Location): CurrentWeather
    fun fetchForecast(loc: Location): ForecastWeather
}