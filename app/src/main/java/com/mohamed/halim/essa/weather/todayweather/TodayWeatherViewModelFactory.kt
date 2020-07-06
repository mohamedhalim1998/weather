package com.mohamed.halim.essa.weather.todayweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohamed.halim.essa.weather.data.Repository

class TodayWeatherViewModelFactory(val date: Long,val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodayWeatherViewModel::class.java)){
            return TodayWeatherViewModel(date, repository) as T
        }
        throw IllegalArgumentException()
    }


}