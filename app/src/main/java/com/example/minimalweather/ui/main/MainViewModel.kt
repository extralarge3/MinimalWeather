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
) : ViewModel(), WeatherItemListener {
    val weatherList: LiveData<List<CurrentWeather>>
        get() = _weatherList
    val _weatherList = MutableLiveData<List<CurrentWeather>>(emptyList())

    init {
        getCurrentWeatherList()
    }

    fun addTestdata(){
        addLocation("Budapest")
        addLocation("London")
        addLocation("Barcelona")
        addLocation("Tokyo")
        addLocation("Sydney")
        getCurrentWeatherList()
    }

    fun getCurrentWeatherList() {
        viewModelScope.launch {
            val tempList = mutableListOf<CurrentWeather>()
            repository.loadCurrentWeatherForAll(
                onStart = {},
                onCompletion = {},
                onError = {},
                forceUpdate = false
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
            //getCurrentWeatherList()
        }
    }
    fun removeLocation(loc: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeLocation(Location(loc))
            //getCurrentWeatherList()
        }
    }

    override fun onItemClicked(item: CurrentWeather) {
        Log.e("TEST", "clicked")
    //TODO("Not yet implemented")
    }

}