package com.example.minimalweather.Model

data class ForecastWeather(
    val forecast: List<WeatherData>,
    val location: Location
) {}