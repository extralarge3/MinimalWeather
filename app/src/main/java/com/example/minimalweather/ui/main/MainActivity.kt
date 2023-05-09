package com.example.minimalweather.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.minimalweather.ui.theme.MinimalWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

//git flow test

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinimalWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android", viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, viewModel: MainViewModel? = null, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
    Button(onClick = {
        //viewModel.

    }) {
        Text(text = "Test Button")
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MinimalWeatherTheme {
        Greeting("Android")
    }
}