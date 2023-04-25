package com.example.minimalweather.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minimalweather.Model.CurrentWeather
import com.example.minimalweather.Model.Location

class MainViewModel(
    repository: MainRepository
) : ViewModel() {
    val currentWeatherList: MutableLiveData<List<CurrentWeather>> = TODO()

    fun getCurrentWeatherList() {}
    fun addLocation(loc: String) {}
    fun removeLocation(loc: Location) {}
}