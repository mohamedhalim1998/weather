package com.mohamed.halim.essa.weather.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mohamed.halim.essa.weather.data.DataSource
import com.mohamed.halim.essa.weather.data.DayForecast
import com.mohamed.halim.essa.weather.data.WeatherResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.SchedulerSupport.IO
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource : DataSource {
    private val BASE_URL = "http://api.openweathermap.org/"

    private val apiService: ApiService by lazy {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

    override fun getWeatherData(): Single<List<DayForecast>> {
        return apiService.getFiveDaysWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { weatherResponse ->
                weatherResponse.daysForecast
            }
    }

}
