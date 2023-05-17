package com.example.minimalweather.model

data class ForecastWeather(
    val forecast: List<WeatherData>,
    val location: Location
) {}