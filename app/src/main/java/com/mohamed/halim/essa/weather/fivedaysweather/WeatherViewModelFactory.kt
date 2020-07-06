package com.mohamed.halim.essa.weather.fivedaysweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohamed.halim.essa.weather.data.Repository
import com.mohamed.halim.essa.weather.data.remote.RemoteDataSource
import java.lang.IllegalArgumentException

class WeatherViewModelFactory(val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException()
    }

}