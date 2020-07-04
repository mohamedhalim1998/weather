package com.mohamed.halim.essa.weather.fivedaysweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohamed.halim.essa.weather.data.remote.RemoteDataSource
import java.lang.IllegalArgumentException

class WeatherViewModelFactory(val source: RemoteDataSource) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            return WeatherViewModel(source) as T
        }
        throw IllegalArgumentException()
    }

}