package com.example.minimalweather.Persistence

import com.example.minimalweather.Model.CurrentWeather

interface CurrentWeatherDao {
    fun insertWeatherRecords(records: List<CurrentWeather>)

    fun getAll(): List<CurrentWeather>

    fun deleteAll()
}