package com.example.minimalweather.Model

import com.example.minimalweather.Model.WeatherData

data class CurrentWeather(
   val weatherData: WeatherData,
   val location: Location
) {}