package com.example.minimalweather.utils

import android.content.Context
import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import com.example.minimalweather.model.network.WeatherDataNetwork
import com.example.minimalweather.model.WeatherData
import com.example.minimalweather.R

fun weatherWetworkToDB(data: WeatherDataNetwork) = WeatherData(
    timestamp = data.dt ?: 0,
    timeZone = data.timezone ?: 0,
    country = data.sys?.country ?: "NA",
    locationName = data.name ?: "NA",
    temp = data.main?.temp ?: 0.0,
    minTemp = data.main?.tempMin ?: 0.0,
    maxTemp = data.main?.tempMax ?: 0.0,
    feelsLike = data.main?.feelsLike ?: 0.0,
    main = data.weather[0].main ?: "unknown",
    condition = data.weather[0].description ?: "unknown",
    iconId = data.weather[0].icon ?: "unknown",
    rain = data.rain?.oneHour ?: 0.0,
    humidity = data.main?.humidity ?: 0,
    pressure = data.main?.pressure ?: 0,
    cloudiness = data.clouds?.all ?: 0,
    windSpeed = data.wind?.speed ?: 0.0,
    windGust = data.wind?.gust ?: 0.0,
    sunrise = data.sys?.sunrise ?: 0,
    sunset = data.sys?.sunset ?: 0,
)

fun weatherToColor(main: String, icon_id: String, context: Context): Int{
    return when(main){
        "Thunderstorm" -> ResourcesCompat.getColor(context.resources, R.color.Thunderstorm, null)
        "Drizzle"-> ResourcesCompat.getColor(context.resources, R.color.Drizzle, null)
        "Rain"-> ResourcesCompat.getColor(context.resources, R.color.Rain, null)
        "Atmosphere"-> ResourcesCompat.getColor(context.resources, R.color.Atmosphere, null)
        "Clear"-> return when(icon_id) {
            "01d" -> ResourcesCompat.getColor(context.resources, R.color.Clear, null)
            "01n" -> ResourcesCompat.getColor(context.resources, R.color.Clear_night, null)
            else -> Color.RED
        }
        "Clouds"-> ResourcesCompat.getColor(context.resources, R.color.Clouds, null)
        "Snow"-> ResourcesCompat.getColor(context.resources, R.color.Snow, null)
        else -> Color.RED
    }
}

fun weatherToTextColor(main: String, icon_id: String, context: Context): Int{
    return when(main){
        "Thunderstorm" -> ResourcesCompat.getColor(context.resources, R.color.primaryTextColor, null)
        "Drizzle"-> ResourcesCompat.getColor(context.resources, R.color.darkTextColor, null)
        "Rain"-> ResourcesCompat.getColor(context.resources, R.color.primaryTextColor, null)
        "Atmosphere"-> ResourcesCompat.getColor(context.resources, R.color.primaryTextColor, null)
        "Clear"-> return when(icon_id) {
            "01d" -> ResourcesCompat.getColor(context.resources, R.color.darkTextColor, null)
            "01n" -> ResourcesCompat.getColor(context.resources, R.color.primaryTextColor, null)
            else -> Color.RED
        }
        "Clouds"-> ResourcesCompat.getColor(context.resources, R.color.primaryTextColor, null)
        "Snow"-> ResourcesCompat.getColor(context.resources, R.color.darkTextColor, null)
        else -> Color.RED
    }
}

fun weatherToIcon(icon_id: String, context: Context): String{
    when(icon_id){
        "01d" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_day_sunny)
        "01n" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_night_clear)
        "02d" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_day_cloudy)
        "02n" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_night_alt_cloudy)
        "03d" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_cloud)
        "03n" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_cloud)
        "04d" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_cloudy)
        "04n" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_cloudy)
        "09d" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_day_showers)
        "09n" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_night_showers)
        "10d" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_day_rain)
        "10n" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_night_rain)
        "11d" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_day_thunderstorm)
        "11n" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_night_thunderstorm)
        "13d" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_day_snow)
        "13n" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_night_snow)
        "50d" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_day_fog)
        "50n" -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_night_fog)
        else  -> return context.getString(com.github.pwittchen.weathericonview.library.R.string.wi_alien)
    }
}