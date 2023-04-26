package com.example.minimalweather.Model

import java.util.Date

data class WeatherData(
    val timeStamp: Date,
    val temp: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val feelsLike: Double,
    val condition: String,
    val rain: Double,
    val humidity: Double,
    val pressure: Double,
    val cloudiness: Double,
    val wind: Double,
    val Sunrise: Date,
    val Sunset: Date
) {}