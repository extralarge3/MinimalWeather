package com.example.minimalweather

import com.example.minimalweather.model.network.Clouds
import com.example.minimalweather.model.network.Coord
import com.example.minimalweather.model.network.Main
import com.example.minimalweather.model.network.Rain
import com.example.minimalweather.model.network.Sys
import com.example.minimalweather.model.network.Weather
import com.example.minimalweather.model.network.WeatherDataNetwork
import com.example.minimalweather.model.network.Wind
import com.example.minimalweather.network.WeatherService
import com.google.gson.Gson
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.skydoves.sandwich.toSuspendFlow
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherServiceTest {
    var dataNetwork = WeatherDataNetwork(
        coord = Coord(lon = 12.0, lat = 12.0),
        weather = listOf(Weather(id = 123, main = "asdsd", description = "assdad", icon = "aassd")),
        base = "asd",
        main = Main(
            temp = 12.0,
            feelsLike = 34.0,
            tempMin = 45.23,
            tempMax = 45.6,
            pressure = 45,
            humidity = 78,
            seaLevel = 34,
            grndLevel = 675
        ),
        visibility = 34,
        wind = Wind(speed = 23.5, deg = 34, gust = 34.0),
        rain = Rain(oneHour = 23.0),
        clouds = Clouds(all = 34),
        dt = 56756756,
        sys = Sys(type = 11, id = 11, country = "GB", sunrise = 1322344, sunset = 324342),
        timezone = 23,
        id = 546,
        name = "dsaaas",
        cod = 234
    )

    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: WeatherService
    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build().create(WeatherService::class.java)
    }

    @Test
    fun getWeatherDataAPITest(){
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody(gson.toJson(dataNetwork)))
            val response = apiService.fetchCurrentWeather(loc = "asd", units = "asd")
            //val request = mockWebServer.takeRequest()

            if(response is ApiResponse.Success){
                assertEquals(dataNetwork, response.data)
            }
            else{
                assertEquals(true, false)
            }
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


}