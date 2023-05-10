package com.example.minimalweather.Persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.minimalweather.Model.Location
import com.example.minimalweather.Model.LocationsWithCurrentWeathers

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

    @Transaction
    @Query("SELECT * FROM location")
    fun getLocationsWithCurrentWeathers(): List<LocationsWithCurrentWeathers>

    @Transaction
    @Query("SELECT * FROM location WHERE name = :loc")
    fun getCurrentWeathersForLocation(loc: String): List<LocationsWithCurrentWeathers>
}

object LoactionDaoMock : LocationDao{
    override fun insertLocation(loc: Location) {
        TODO("Not yet implemented")
    }

    override fun updateLocation(loc: Location) {
        TODO("Not yet implemented")
    }

    override fun deleteLocation(loc: Location) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Location> {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun getLocationsWithCurrentWeathers(): List<LocationsWithCurrentWeathers> {
        TODO("Not yet implemented")
    }

    override fun getCurrentWeathersForLocation(loc: String): List<LocationsWithCurrentWeathers> {
        TODO("Not yet implemented")
    }

}