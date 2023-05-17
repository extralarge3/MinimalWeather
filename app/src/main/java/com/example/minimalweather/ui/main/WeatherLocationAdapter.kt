package com.example.minimalweather.ui.main

import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.R
import com.example.minimalweather.databinding.WeatherItemBinding
import com.example.minimalweather.ui.common.BaseAdapter

class WeatherLocationAdapter(
    private val list: List<CurrentWeather>,
    private val itemListener: WeatherItemListener
) : BaseAdapter<WeatherItemBinding, CurrentWeather>(list){

    override val layoutId: Int = R.layout.weather_item

    override fun bind(binding: WeatherItemBinding, item: CurrentWeather) {
        binding.apply {
            weather = item
            listener = itemListener
            executePendingBindings()
        }
    }
}

interface WeatherItemListener {
    fun onItemClicked(item: CurrentWeather)
}
