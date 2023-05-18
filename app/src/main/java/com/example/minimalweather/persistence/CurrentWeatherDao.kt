package com.example.minimalweather.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.minimalweather.model.CurrentWeather

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherRecords(records: List<CurrentWeather>)

    @Query("SELECT * FROM currentweather")
    fun getAll(): List<CurrentWeather>

    @Query("SELECT * FROM currentweather WHERE location_id = :locId")
    fun getWeatherForLocation(locId : Int): List<CurrentWeather>

    @Query("DELETE FROM currentweather")
    fun deleteAll()
}