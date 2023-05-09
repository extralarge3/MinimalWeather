package com.example.minimalweather.Model

import androidx.room.ColumnInfo
import java.util.Date

data class WeatherData(
    val timestamp: String,
    val temp: Double,
    @ColumnInfo(name = "mintemp") val minTemp: Double,
    @ColumnInfo(name = "maxtemp") val maxTemp: Double,
    @ColumnInfo(name = "feelslike") val feelsLike: Double,
    val condition: String,
    val rain: Double,
    val humidity: Double,
    val pressure: Double,
    val cloudiness: Double,
    val wind: Double,
    val sunrise: String,
    val sunset: String
) {}