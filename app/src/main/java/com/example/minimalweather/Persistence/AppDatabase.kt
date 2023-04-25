package com.example.minimalweather.Persistence

abstract class AppDatabase {
    abstract fun locationDao(): LocationDao
    abstract fun currentWeatherDao(): CurrentWeatherDao
}