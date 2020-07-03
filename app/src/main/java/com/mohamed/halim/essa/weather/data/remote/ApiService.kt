package com.mohamed.halim.essa.weather.data.remote

import com.mohamed.halim.essa.weather.data.WeatherResponse
import com.mohamed.halim.essa.weather.utils.API_KEY
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET


interface ApiService {
    @GET("data/2.5/forecast?id=360630&appid=$API_KEY")
    fun getFiveDaysWeather() : Single<WeatherResponse>
}

