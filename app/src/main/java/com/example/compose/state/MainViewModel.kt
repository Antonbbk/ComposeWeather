package com.example.compose.state

import androidx.lifecycle.viewModelScope
import com.example.compose.model.MainState
import com.example.compose.model.Weather
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel() : StateViewModel<MainState, MainError>() {

    init {
        val client = HttpClient()

        viewModelScope.launch {
            val data = client.get<String>(API_MINSK)
            val gson = Gson()
            val weather = gson.fromJson(data, Weather::class.java)

            delay(1000)
            state.value = state.value.copy(data = MainState(weather.current.temp_c.toString()))
            state.loaded()

        }
    }
}

sealed class MainError : AppError