package com.mohamed.halim.essa.weather.fivedaysweather

import androidx.lifecycle.ViewModel
import com.mohamed.halim.essa.weather.data.remote.RemoteDataSource

class WeatherViewModel(source: RemoteDataSource) : ViewModel() {
    val weatherDate by lazy {
        source.getWeatherData()
    }
}