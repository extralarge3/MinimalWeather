package com.example.minimalweather.Model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.minimalweather.Model.WeatherData

@Entity(tableName = "currentweather")
data class CurrentWeather(
   @PrimaryKey val id: String,
   @Embedded val weatherData: WeatherData,
   @ColumnInfo(name = "location_id") val locationID: Int
) {}