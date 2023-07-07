package com.example.minimalweather


import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.model.Location
import com.example.minimalweather.model.LocationsWithCurrentWeathers
import com.example.minimalweather.model.WeatherData
import com.example.minimalweather.persistence.AppDatabase
import com.example.minimalweather.persistence.CurrentWeatherDao
import com.example.minimalweather.persistence.LocationDao
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DBtest {
    lateinit var db: AppDatabase
    lateinit var weatherDao: CurrentWeatherDao
    lateinit var locDao: LocationDao

    val testloc = Location(name = "Rosalie Tate", id = 6559)
    val testloc2 = Location(name = "ASDASD", id = 2345)

    var currWeatherTest = CurrentWeather(
        id = 7156,
        weatherData = WeatherData(
            timestamp = 5113,
            timeZone = 7608,
            country = "Peru",
            locationName = "Leonel Graham",
            temp = 0.1,
            minTemp = 2.3,
            maxTemp = 4.5,
            feelsLike = 6.7,
            condition = "wisi",
            main = "feugait",
            iconId = "eruditi",
            rain = 8.9,
            humidity = 7786,
            pressure = 3524,
            cloudiness = 6047,
            windSpeed = 10.11,
            windGust = 12.13,
            sunrise = 3947,
            sunset = 9898
        ),
        locationID = 6559
    )

    var currWeatherTest2 = CurrentWeather(
        id = 7155,
        weatherData = WeatherData(
            timestamp = 515413,
            timeZone = 76408,
            country = "HU",
            locationName = "Leonel Graham",
            temp = 0.1,
            minTemp = 52.3,
            maxTemp = 498.5,
            feelsLike = 896.7,
            condition = "wisi",
            main = "feugait",
            iconId = "eruditi",
            rain = 8.9,
            humidity = 77486,
            pressure = 3524,
            cloudiness = 60447,
            windSpeed = 10.11,
            windGust = 1254.13,
            sunrise = 35947,
            sunset = 98598
        ),
        locationID = 2345
    )


    @Before
    fun setupDatabase() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        weatherDao = db.currentWeatherDao()
        locDao = db.locationDao()
    }

    @Test
    fun getLocationsWithWeathersTest(){
        runBlocking {
            locDao.insertLocation(testloc)
            locDao.insertLocation(testloc2)
            weatherDao.insertWeatherRecords(listOf(currWeatherTest, currWeatherTest2))

            val locsWithWeathers = locDao.getLocationsWithCurrentWeathers()

            val expectedLocsWithWeathers = listOf(
                LocationsWithCurrentWeathers(
                    location = testloc,
                    weathers = listOf(currWeatherTest)
                ),
                LocationsWithCurrentWeathers(
                    location = testloc2,
                    weathers = listOf(currWeatherTest2)
                ),
            )

            assertEquals(expectedLocsWithWeathers.toSet(), locsWithWeathers.toSet())
        }
    }

    @After
    fun closeDatabase() {
        db.close()
    }

}