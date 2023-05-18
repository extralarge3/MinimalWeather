package com.example.minimalweather.ui.details

import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.model.Location
import com.example.minimalweather.model.WeatherData
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
    suspend fun getDetailed1Weather(locId: Int): CurrentWeather {
        val locWithWeather = locationDao.getCurrentWeathersForLocation(loc=locId)
        if(locWithWeather.isNotEmpty()){
            return locWithWeather.first().weathers.first()
        } else{
            return CurrentWeather(
                id = 0, weatherData = WeatherData(
                    timestamp = 0,
                    timeZone = 0,
                    country = "",
                    locationName = "",
                    temp = 0.0,
                    minTemp = 0.0,
                    maxTemp = 0.0,
                    feelsLike = 0.0,
                    condition = "",
                    main = "",
                    iconId = "",
                    rain = 0.0,
                    humidity = 0,
                    pressure = 0,
                    cloudiness = 0,
                    windSpeed = 0.0,
                    windGust = 0.0,
                    sunrise = 0,
                    sunset = 0
                ), locationID = 0
            )
        }
    }
}