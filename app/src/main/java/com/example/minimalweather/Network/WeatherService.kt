package com.example.minimalweather.Network

import com.example.minimalweather.Model.CurrentWeather
import com.example.minimalweather.Model.ForecastWeather
import com.example.minimalweather.Model.Location
import com.example.minimalweather.Model.Network.WeatherDataNetwork
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun fetchCurrentWeather(@Query("q") loc: String, @Query("units") units : String = "metric"): ApiResponse<WeatherDataNetwork>
    //fun fetchForecast(loc: Location): ForecastWeather
}

object MockWeatherService : WeatherService{
    override suspend fun fetchCurrentWeather(
        loc: String ,
        units: String
    ): ApiResponse<WeatherDataNetwork> {
        return TODO()
    }
}