package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.compose.model.Forecast
import com.example.compose.model.Weather
import com.example.compose.state.MainViewModel
import kotlinx.coroutines.NonCancellable.message
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            NewText(mainViewModel.state.value.toString())

            DaysWeatherList()

        }
    }
}

@Composable
fun NewText(name: String) {
    Text(text = "$name!")
}

@Composable
fun DaysWeatherList(weather: List<String>) {
    LazyColumn {
        items(10) { index ->
            Text(text = "Item: $index")
        }
    }
}