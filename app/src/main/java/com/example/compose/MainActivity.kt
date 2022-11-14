package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewText("Android")
        }
    }
}

@Composable
fun NewText(name: String){
    Text(text = "Hello! $name!", fontFamily = null, color = Color.Red)
}

@Preview (showBackground = true)
@Composable
fun PreviewMessageCard() {
    NewText(name = "Android")
}

private fun requestWeatherData(city:String){
    
}

