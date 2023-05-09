package com.example.minimalweather.Persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.minimalweather.Model.CurrentWeather
import com.example.minimalweather.Model.Location
import com.example.minimalweather.Model.WeatherData

@Database(entities = [Location::class, CurrentWeather::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun currentWeatherDao(): CurrentWeatherDao
}