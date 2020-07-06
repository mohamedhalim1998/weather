package com.mohamed.halim.essa.weather.todayweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohamed.halim.essa.weather.data.DayForecast
import com.mohamed.halim.essa.weather.data.Repository

class TodayWeatherViewModel(date: Long, repository: Repository) : ViewModel() {
    val forecast = repository.getDayForecast(date)
}