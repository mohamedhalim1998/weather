package com.mohamed.halim.essa.weather.data

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single


interface DataSource {
    fun getWeatherData() : Flowable<List<DayForecast>>
}