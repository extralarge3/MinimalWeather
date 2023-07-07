package com.example.minimalweather.ui.main

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.model.Location
import com.example.minimalweather.network.WeatherService
import com.example.minimalweather.persistence.CurrentWeatherDao
import com.example.minimalweather.persistence.LocationDao
import com.example.minimalweather.utils.weatherWetworkToDB
import com.skydoves.sandwich.message
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
        if(forceUpdate){
            currentWeatherDao.deleteAll()
        }
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

    suspend fun removeLocation(locId: Int){
        locationDao.deleteLocation(locId)
    }

    suspend fun removeAllWeathers(){
        currentWeatherDao.deleteAll()
    }

    suspend fun removeAllLocations(){
        locationDao.deleteAll()
    }

}