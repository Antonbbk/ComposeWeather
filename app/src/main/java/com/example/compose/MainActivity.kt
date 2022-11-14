package com.example.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewText("Minsk")
            simpleCase()
        }
    }
}

@Composable
fun NewText(name: String) {
    Text(text = "$name!", fontFamily = null, color = Color.Red)
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherCard() {
    NewText(name = "Minsk")
}

private const val API_MINSK =
    "http://api.weatherapi.com/v1/current.json?key=345f952bf95a4c04a9b164321221411&q=Minsk"

data class Weather(
    val current: Current
)

data class Current(
    val temp_c: Double
)

fun simpleCase() {

    val client = HttpClient()

    GlobalScope.launch(Dispatchers.IO) {
        val data = client.get<String>(API_MINSK)
        val gson = Gson()
        val weather = gson.fromJson(data, Weather::class.java)
        Log.i(" Serialization", weather.toString())
    }
}





