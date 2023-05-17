package com.example.minimalweather.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.model.ForecastWeather
import com.example.minimalweather.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val repository: DetailsRepository
) :ViewModel() {
    val forecast: MutableLiveData<ForecastWeather> = TODO()

    val currentWeather: MutableLiveData<CurrentWeather> = TODO()

    fun getForecast(loc: Location){}
    fun getCurrentWeather(loc: Location){}
}