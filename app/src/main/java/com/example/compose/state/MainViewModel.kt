package com.example.compose.state

import androidx.lifecycle.viewModelScope
import com.example.compose.model.MainState
import com.example.compose.repository.WeatherRepository
import com.example.compose.repository.WeatherRepositoryError
import com.example.compose.repository.WeatherRepositoryImp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val weatherRepository: WeatherRepository = WeatherRepositoryImp(),
) : StateViewModel<MainState, MainError>() {



    init {

        getData()
        getDataList()
        viewModelScope.launch {

            weatherRepository.weatherFlow.collect { weatherString ->
                weatherString?.let {
                    delay(4000)
                    state.value = state.value.copy(data = MainState(weatherString))
                    state.loaded()
                }
            }


        }
        viewModelScope.launch {
            weatherRepository.weekWeatherFlow.collect{

            }
        }
    }

    fun getData() {
        state.loading()
        viewModelScope.launch {

            val result = weatherRepository.getWeather()

            state.loaded(result.toAppError())
        }
    }

    fun getDataList(){
        state.loading()
        viewModelScope.launch {
            val result =weatherRepository.getWeatherDays()

            state.loaded(result.toAppError())
        }
    }
}


fun WeatherRepositoryError?.toAppError(): MainError? {
    return when (this) {
        is WeatherRepositoryError.Json -> MainError.Json
        else -> null
    }
}

sealed class MainError : AppError {
    object Json : MainError()
}