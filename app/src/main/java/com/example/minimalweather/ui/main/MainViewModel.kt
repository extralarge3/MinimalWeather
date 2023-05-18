package com.example.minimalweather.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: MainRepository
) : ViewModel() {
    val weatherList: LiveData<List<CurrentWeather>>
        get() = _weatherList
    val _weatherList = MutableLiveData<List<CurrentWeather>>(emptyList())

    init {
        getCurrentWeatherList()
    }

    fun getCurrentWeatherList(forceUpdate : Boolean = false) {
        viewModelScope.launch {
            val tempList = mutableListOf<CurrentWeather>()
            repository.loadCurrentWeatherForAll(
                onStart = {},
                onCompletion = {},
                onError = {},
                forceUpdate = forceUpdate
            )
                .collect { element ->
                tempList.add(element)
                    _weatherList.value = tempList.toList()
            }
        }
    }
    fun addLocation(loc: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLocation(Location(loc))
            getCurrentWeatherList()
        }
    }
    fun removeLocation(locId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeLocation(locId)
            getCurrentWeatherList()
        }
    }
}