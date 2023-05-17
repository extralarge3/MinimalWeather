package com.example.minimalweather.model

import androidx.room.Embedded
import androidx.room.Relation

data class LocationsWithCurrentWeathers (
    @Embedded val location : Location,
    @Relation(
        parentColumn = "id",
        entityColumn = "location_id"
    )
    val weathers: List<CurrentWeather>
) {}