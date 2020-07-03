package com.mohamed.halim.essa.weather.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface DataSource {
    fun getWeatherData() : Single<List<DayForecast>>
}