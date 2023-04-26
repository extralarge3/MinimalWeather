package com.example.minimalweather.Model

data class CurrentWeather(
   val weatherData: WeatherData,
   val location: Location
) {}