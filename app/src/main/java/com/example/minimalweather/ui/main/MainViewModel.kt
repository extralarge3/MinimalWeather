package com.example.minimalweather.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minimalweather.Model.CurrentWeather
import com.example.minimalweather.Model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    repository: MainRepository
) : ViewModel() {
    val currentWeatherList: MutableLiveData<List<CurrentWeather>> = TODO()

    fun getCurrentWeatherList() {}
    fun addLocation(loc: String) {}
    fun removeLocation(loc: Location) {}
}