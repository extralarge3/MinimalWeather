package com.example.minimalweather

import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.model.Location
import com.example.minimalweather.model.LocationsWithCurrentWeathers
import com.example.minimalweather.model.WeatherData
import com.example.minimalweather.model.network.Clouds
import com.example.minimalweather.model.network.Coord
import com.example.minimalweather.model.network.Main
import com.example.minimalweather.model.network.Rain
import com.example.minimalweather.model.network.Sys
import com.example.minimalweather.model.network.Weather
import com.example.minimalweather.model.network.WeatherDataNetwork
import com.example.minimalweather.model.network.Wind
import com.example.minimalweather.network.WeatherService
import com.example.minimalweather.persistence.CurrentWeatherDao
import com.example.minimalweather.persistence.LocationDao
import com.example.minimalweather.ui.main.MainRepository
import com.example.minimalweather.utils.weatherWetworkToDB
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.SandwichInitializer
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MainRepositoryTest {
    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: WeatherService

    @Mock
    lateinit var currWeatherDao: CurrentWeatherDao

    @Mock
    lateinit var locDao: LocationDao

    var currWeatherTest = CurrentWeather(
        id = 7156, weatherData = WeatherData(
            timestamp = 5113,
            timeZone = 7608,
            country = "Peru",
            locationName = "Leonel Graham",
            temp = 0.1,
            minTemp = 2.3,
            maxTemp = 4.5,
            feelsLike = 6.7,
            condition = "wisi",
            main = "feugait",
            iconId = "eruditi",
            rain = 8.9,
            humidity = 7786,
            pressure = 3524,
            cloudiness = 6047,
            windSpeed = 10.11,
            windGust = 12.13,
            sunrise = 3947,
            sunset = 9898
        ), locationID = 9252
    )

    val locwWeather =LocationsWithCurrentWeathers(
        location = Location(name = "Test", id = 1423),
        weathers = listOf(currWeatherTest)
    )

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

    val locwWeather2 =LocationsWithCurrentWeathers(
        location = Location(name = "Test", id = 123),
        weathers = listOf()
    )

    val locWeather2exp = listOf(
            CurrentWeather(
                id = 0, weatherData = weatherWetworkToDB(dataNetwork), locationID = 123
            )
    )



    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = MainRepository(apiService, locDao, currWeatherDao)
    }

    @Test
    fun loadCurrentWeatherForAllTest() {
        runBlocking {
            //Mockito.`when`(apiService.).thenReturn(Response.success(listOf<Movie>()))
            Mockito.`when`(locDao.getLocationsWithCurrentWeathers()).thenReturn(
                listOf(
                    locwWeather
                )
            )

            val res = mainRepository.loadCurrentWeatherForAll(
                onStart = {},
                onCompletion = {},
                onError = {},
                forceUpdate = false
            )


            assertEquals(locwWeather.weathers, res.toList())
        }
    }

    @Test
    fun loadCurrentWeatherForAllTest2() {
        runBlocking {
            //Mockito.`when`(apiService.).thenReturn(Response.success(listOf<Movie>()))
            Mockito.`when`(locDao.getLocationsWithCurrentWeathers()).thenReturn(
                listOf(
                    locwWeather2
                )
            )
            Mockito.`when`(apiService.fetchCurrentWeather(loc = locwWeather2.location.name)).thenReturn(
                ApiResponse.of(SandwichInitializer.successCodeRange){
                    Response.success(dataNetwork)
                }
            )

            val res = mainRepository.loadCurrentWeatherForAll(
                onStart = {},
                onCompletion = {},
                onError = {},
                forceUpdate = false
            )
            val test = res.toList()
            print(test)

            assertEquals(locWeather2exp, res.toList())
        }
    }
}