package com.example.minimalweather.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.minimalweather.model.Location
import com.example.minimalweather.model.LocationsWithCurrentWeathers

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(loc: Location)

    @Update
    fun updateLocation(loc: Location)

    @Query("DELETE FROM location WHERE id = :locId")
    fun deleteLocation(locId: Int)

    @Query("SELECT * FROM location")
    fun getAll(): List<Location>

    @Query("DELETE FROM location")
    fun deleteAll()

    @Transaction
    @Query("SELECT * FROM location")
    fun getLocationsWithCurrentWeathers(): List<LocationsWithCurrentWeathers>

    @Transaction
    @Query("SELECT * FROM location WHERE id = :loc")
    fun getCurrentWeathersForLocation(loc: Int): List<LocationsWithCurrentWeathers>
}