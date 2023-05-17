package com.example.minimalweather.ui.details

import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.network.WeatherService
import com.example.minimalweather.persistence.CurrentWeatherDao
import com.example.minimalweather.persistence.LocationDao
import com.example.minimalweather.utils.weatherWetworkToDB
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val currentWeatherDao: CurrentWeatherDao,
    private val locationDao: LocationDao
) {
    suspend fun getDetailedWeather(loc: String): CurrentWeather {
        val locWithWeather = locationDao.getCurrentWeathersForLocation(loc=loc)

        if(locWithWeather.isEmpty()){
            val response = weatherService.fetchCurrentWeather(loc=loc)
            if (response is ApiResponse.Success) {
                val weatherData = weatherWetworkToDB(response.data)
                val currWeather = CurrentWeather(
                    weatherData = weatherData,
                    locationID = locWithWeather.first().location.id
                )
                currentWeatherDao.insertWeatherRecords(listOf(currWeather))
                return currWeather
            }
            else{
                throw Exception()
            }
        }
        else{
            return locWithWeather.first().weathers.first()
        }
    }
}