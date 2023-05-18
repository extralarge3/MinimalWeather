package com.example.minimalweather.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.minimalweather.R
import com.example.minimalweather.databinding.ActivityDetailsBinding
import com.example.minimalweather.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    val viewModel by viewModels<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.apply {
            viewModel = this@DetailsActivity.viewModel
            lifecycleOwner = this@DetailsActivity
        }
        setContentView(binding.root)

        val place_id = intent.getIntExtra("LOCATION_ID", -1)
        viewModel.getCurrentWeather(place_id)
    }

}