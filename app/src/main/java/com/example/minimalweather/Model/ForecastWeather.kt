package com.example.minimalweather.Model

import com.example.minimalweather.Model.WeatherData

data class ForecastWeather(
    val forecast: List<WeatherData>,
    val location: Location
) {}