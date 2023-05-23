package com.example.minimalweather

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.model.WeatherData
import com.example.minimalweather.network.WeatherService
import com.example.minimalweather.persistence.CurrentWeatherDao
import com.example.minimalweather.persistence.LocationDao
import com.example.minimalweather.ui.main.MainRepository
import com.example.minimalweather.ui.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


//valamiért hibát dob a mockito elvileg így kéne pedig
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var mainViewModel: MainViewModel
    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: WeatherService

    @Mock
    lateinit var currWeatherDao: CurrentWeatherDao

    @Mock
    lateinit var locDao: LocationDao

    //@get:Rule
    //val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

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


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = MainRepository(apiService, locDao, currWeatherDao)
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun getAllWeatherTest() {
        runBlocking {
            Mockito.`when`(mainRepository.loadCurrentWeatherForAll(
                onStart = {},
                onCompletion = {},
                onError = {},
                forceUpdate = false
            ))
                .thenReturn(flow {
                    emit(currWeatherTest)
                })
            mainViewModel.getCurrentWeatherList()

            val result = mainViewModel.weatherList.getOrAwaitValue()
            assertEquals("TEST", listOf(currWeatherTest), result)
        }
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    try {
        afterObserve.invoke()
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }
    } finally {
        this.removeObserver(observer)
    }
    @Suppress("UNCHECKED_CAST")
    return data as T
}