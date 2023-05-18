package com.example.minimalweather.model

data class WeatherData(
    val timestamp: Int,
    val timeZone: Int,
    val country: String,
    val locationName: String,
    val temp: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val feelsLike: Double,
    val condition: String,
    val main: String,
    val iconId: String,
    val rain: Double,
    val humidity: Int,
    val pressure: Int,
    val cloudiness: Int,
    val windSpeed: Double,
    val windGust: Double,
    val sunrise: Int,
    val sunset: Int
) {}