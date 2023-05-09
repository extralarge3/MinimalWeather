package com.example.minimalweather.ui.main

import android.os.Bundle
import android.util.Log
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
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
                    Greeting("Android")
                }
            }
        }

        GlobalScope.launch {
            val response = viewModel!!.networktest()
            response.suspendOnSuccess {
                Log.e("TEST", data.toString())
            }.suspendOnError {
                Log.e("TEST", message().toString())
            }.suspendOnException {
                Log.e("TEST", "welll")
            // handles exceptional cases
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
//    Button(onClick = {
//        GlobalScope.launch {
//            val response = viewModel!!.networktest()
//            response.suspendOnSuccess {
//                Log.d("TEST", data.toString())
//            }.suspendOnError {
//                Log.d("TEST", "network error")
//            }.suspendOnException {
//                Log.d("TEST", "welll")
//            // handles exceptional cases
//            }
//        }
//
//
//    }) {
//        Text(text = "Test Button")
//    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MinimalWeatherTheme {
        Greeting("Android")
    }
}