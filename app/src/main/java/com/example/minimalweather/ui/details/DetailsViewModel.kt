package com.example.minimalweather.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.model.ForecastWeather
import com.example.minimalweather.model.Location
import com.example.minimalweather.model.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val repository: DetailsRepository
) :ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeather>(
        CurrentWeather(
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
    )
    val currentWeather : LiveData<CurrentWeather>
        get() = _currentWeather

    fun getCurrentWeather(locId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val weather = repository.getDetailed1Weather(locId)
            withContext(Dispatchers.Main){
                _currentWeather.value = weather
                Log.e("test", locId.toString())
                Log.e("test", weather.toString())
            }
        }
    }
}