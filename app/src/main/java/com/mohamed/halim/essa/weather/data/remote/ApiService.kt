package com.mohamed.halim.essa.weather.data.remote

import com.mohamed.halim.essa.weather.data.ThreeHourResponse
import com.mohamed.halim.essa.weather.data.WeatherResponse
import com.mohamed.halim.essa.weather.utils.API_KEY
import com.mohamed.halim.essa.weather.utils.FORECAST_API_KEY
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {
    @GET("data/2.5/forecast/daily?id=360630&cnt=5&units=metric&appid=$FORECAST_API_KEY")
    fun getFiveDaysWeather(): Flowable<WeatherResponse>

    @GET("data/2.5/forecast?id=360630&&units=metric&appid=$FORECAST_API_KEY")
    fun getThreeHourWeather(): Single<ThreeHourResponse>
}

