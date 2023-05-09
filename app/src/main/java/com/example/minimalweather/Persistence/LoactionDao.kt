package com.example.minimalweather.Persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.minimalweather.Model.Location
@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(loc: Location)
    @Update
    fun updateLocation(loc: Location)
    @Delete
    fun deleteLocation(loc: Location)
    @Query("SELECT * FROM location")
    fun getAll(): List<Location>
    @Query("DELETE FROM location")
    fun deleteAll()
}