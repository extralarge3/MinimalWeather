package com.example.minimalweather.ui.common

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.utils.weatherToColor
import com.example.minimalweather.utils.weatherToIcon
import com.github.pwittchen.weathericonview.WeatherIconView
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