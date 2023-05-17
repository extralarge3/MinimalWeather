package com.example.minimalweather.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.model.Location

@Database(entities = [Location::class, CurrentWeather::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun currentWeatherDao(): CurrentWeatherDao
}