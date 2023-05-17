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

    @Query("DELETE FROM currentweather")
    fun deleteAll()
}

object CurrentWeatherDaoMock : CurrentWeatherDao{
    override fun insertWeatherRecords(records: List<CurrentWeather>) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<CurrentWeather> {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

}