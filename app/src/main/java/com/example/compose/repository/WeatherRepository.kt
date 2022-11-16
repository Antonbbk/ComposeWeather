package com.example.compose.repository

import com.example.compose.model.Forecast
import com.example.compose.model.Weather
import com.example.compose.state.API_DAYS
import com.example.compose.state.API_HOURS
import com.example.compose.state.API_MINSK
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface WeatherRepository {
    val weatherFlow: StateFlow<String?>
    val weekWeatherFlow: StateFlow<List<String>?>

    suspend fun getWeather(): WeatherRepositoryError?

    suspend fun getWeatherByDays(): WeatherRepositoryError?


}


class WeatherRepositoryImp() : WeatherRepository {

    override val weatherFlow: MutableStateFlow<String?> = MutableStateFlow(null)

    override val weekWeatherFlow: StateFlow<List<String>?> = MutableStateFlow(null)


    override suspend fun getWeather(): WeatherRepositoryError? {

        val client = HttpClient()

        val data = client.get<String>(API_MINSK)  // обработать ошибки
        val gson = Gson()
        return try {
            delay(3000)
            val weather = gson.fromJson(data, Weather::class.java)
            val weatherString = weather.current.temp_c.toString()

            weatherFlow.value = weatherString

            null
        } catch (exception: JsonSyntaxException) {
            WeatherRepositoryError.Json
        }
    }

    override suspend fun getWeatherByDays(): WeatherRepositoryError? {
        val client = HttpClient()

        val data = client.get<List<String>>(API_DAYS)
        val gson = Gson()
        return try {
            delay(3000)
            val list = ArrayList<Forecast>()
            val days = mainObject.getJSONObject()
            weatherList =

            weekWeatherFlow.value = weatherList

            null
        } catch (exception: JsonSyntaxException) {
            WeatherRepositoryError.Json
        }

    }
}

sealed class WeatherRepositoryError {
    object Json : WeatherRepositoryError()
}

