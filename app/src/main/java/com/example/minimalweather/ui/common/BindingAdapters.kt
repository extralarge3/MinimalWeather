package com.example.minimalweather.ui.common

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.utils.weatherToColor
import com.example.minimalweather.utils.weatherToIcon
import com.github.pwittchen.weathericonview.WeatherIconView
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.TimeZone
import kotlin.math.roundToInt

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: BaseAdapter<ViewDataBinding, ListAdapterItem>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.updateData(list ?: listOf())
}

@BindingAdapter("backgroundColor")
fun setBackgroundColor(view: CardView, weather: CurrentWeather) {
    view.setCardBackgroundColor(weatherToColor(
        main = weather.weatherData.main,
        icon_id = weather.weatherData.iconId,
        context =view.context)
    )
}

@BindingAdapter("weatherIcon")
fun setWeatherIcon(view: WeatherIconView, weather: CurrentWeather) {
    view.setIconResource(weatherToIcon(icon_id = weather.weatherData.iconId, context =view.context))
}

@BindingAdapter("tempTextValue")
fun setTempTextValue(view: TextView, value : Double) {
    view.text = value.roundToInt().toString()
}


@BindingAdapter(value = ["timeStamp", "timeZone"], requireAll = false)
fun setTimeTextValue(view: TextView, timeStamp : Int, timeZone : Int) {
    val date = Date(timeStamp * 1000L)
    val jdf = SimpleDateFormat("HH:mm")
    val tz_string =
        if(timeZone > 0)
            "+" + timeZone / 3600
        else
            (timeZone / 3600).toString()

    jdf.setTimeZone(TimeZone.getTimeZone("GMT$tz_string"))
    //Log.e("ASD", ("GMT$tz_string"))
    val string_time: String = jdf.format(date)
    view.text = string_time
    //val instant = Instant.ofEpochSecond(value.toLong())
    //val time = LocalTime.ofInstant(instant, ZoneId.systemDefault())
    //val formatter = DateTimeFormatter.ofPattern("HH:mm") // "HH" for 24-hour format, "hh" for 12-hour format

}
