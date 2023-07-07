package com.example.minimalweather.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.minimalweather.R
import com.example.minimalweather.databinding.ActivityMainBinding
import com.example.minimalweather.model.CurrentWeather
import com.example.minimalweather.ui.details.DetailsActivity
import com.example.minimalweather.ui.dialogs.NewLocationDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NewLocationDialog.NewLocationListener, WeatherItemListener {

    private lateinit var binding: ActivityMainBinding
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        binding.adapter = WeatherLocationAdapter(listOf(), this)
        binding.fabAdd.setOnClickListener {
            NewLocationDialog().show(
                supportFragmentManager,
                NewLocationDialog.TAG)
        }
    }

    override fun onPlaceAdded(place: String) {
        viewModel.addLocation(place)
    }

    override fun onItemClicked(item: CurrentWeather) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("LOCATION_ID", item.locationID)
        }
        startActivity(intent)
    }

    override fun onItemLongClicked(weather: CurrentWeather, view: View) : Boolean {
        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.menu_location_long_click)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.delete_place -> {
                    viewModel.removeLocation(weather.locationID)
                }
            }
            false
        }
        popup.show()
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_places, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.refresh -> {
            viewModel.getCurrentWeatherList(forceUpdate = true)
            true
        }
        R.id.clear_all -> {
            viewModel.removeAll()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

}

