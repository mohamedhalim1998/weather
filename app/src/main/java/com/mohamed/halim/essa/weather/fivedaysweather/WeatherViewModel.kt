package com.mohamed.halim.essa.weather.fivedaysweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohamed.halim.essa.weather.data.Repository

class WeatherViewModel(val repository: Repository) : ViewModel() {
    val weatherDate = repository.getWeatherData()
    private val _navigateToDetails = MutableLiveData<Long>()
    val navigateToDetails: LiveData<Long>
        get() = _navigateToDetails

    fun onDayClick(date: Long) {
        _navigateToDetails.value = date
    }

    fun doneNavigating() {
        _navigateToDetails.value = null
    }

    fun changeCity(city: String) {
        repository.changeCity(city)

    }

    fun changeUnit(unit : String) {
        repository.changeUnit(unit)
    }

}