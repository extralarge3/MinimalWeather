package com.example.minimalweather.ui.main

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.minimalweather.Model.CurrentWeather
import com.example.minimalweather.Model.Location
import com.example.minimalweather.Model.Network.WeatherDataNetwork
import com.example.minimalweather.Model.WeatherData
import com.example.minimalweather.Network.WeatherService
import com.example.minimalweather.Persistence.CurrentWeatherDao
import com.example.minimalweather.Persistence.LocationDao
import com.example.minimalweather.utils.weatherWetworkToDB
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val locationDao: LocationDao,
    private val currentWeatherDao: CurrentWeatherDao
) {
    @WorkerThread
    fun loadCurrentWeatherForAll(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit,
        forceUpdate: Boolean = false
    ) = flow{
        val locationsAndWeathers = locationDao.getLocationsWithCurrentWeathers()
        for(loc in locationsAndWeathers){
            if(loc.weathers.isEmpty() or forceUpdate){
                val response = weatherService.fetchCurrentWeather(loc=loc.location.name)
                response.suspendOnSuccess {
                    val weatherData = weatherWetworkToDB(data)
                    val currWeather = CurrentWeather(
                        weatherData = weatherData,
                        locationID = loc.location.id
                    )
                    currentWeatherDao.insertWeatherRecords(listOf(currWeather))
                    emit(currWeather)
                }.suspendOnError {
                    Log.e("TEST", message().toString())
                }.suspendOnException {
                    Log.e("TEST", message().toString())
                    // handles exceptional cases
                }
            }
            else{
                emit(loc.weathers.first())
            }
        }
    }
        .onStart { onStart() }
        .onCompletion { onCompletion() }
        .flowOn(Dispatchers.IO)


    suspend fun addLocation(loc: Location){
        locationDao.insertLocation(loc)
    }

    suspend fun removeLocation(loc: Location){
        locationDao.deleteLocation(loc)
    }

}