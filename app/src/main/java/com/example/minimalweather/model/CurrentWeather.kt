package com.example.minimalweather.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.minimalweather.ui.common.ListAdapterItem

@Entity(tableName = "currentweather")
data class CurrentWeather(
   @PrimaryKey(autoGenerate = true) override val id: Int = 0,
   @Embedded val weatherData: WeatherData,
   @ColumnInfo(name = "location_id") val locationID: Int
) : ListAdapterItem {}