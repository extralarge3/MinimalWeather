package com.example.minimalweather.Persistence

import com.example.minimalweather.Model.Location

interface LocationDao {
    fun insertLocation(loc: Location)

    fun updateLocation(loc: Location)

    fun deleteLocation(loc: Location)

    fun getAll(): List<Location>

    fun deleteAll()
}