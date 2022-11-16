package com.example.compose.model

data class Weather(
    val current: Current,
    val location: Location,
    val forecast: Forecast
)