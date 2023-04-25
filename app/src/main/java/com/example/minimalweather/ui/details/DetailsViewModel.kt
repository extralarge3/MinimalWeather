package com.example.minimalweather.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minimalweather.Model.CurrentWeather
import com.example.minimalweather.Model.ForecastWeather
import com.example.minimalweather.Model.Location

class DetailsViewModel(
    repository: DetailsRepository
) :ViewModel() {
    val forecast: MutableLiveData<ForecastWeather> = TODO()

    val currentWeather: MutableLiveData<CurrentWeather> = TODO()

    fun getForecast(loc: Location){}
    fun getCurrentWeather(loc: Location){}
}