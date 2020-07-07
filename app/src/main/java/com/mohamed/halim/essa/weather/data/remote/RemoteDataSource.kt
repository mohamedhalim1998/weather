package com.mohamed.halim.essa.weather.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mohamed.halim.essa.weather.data.DataSource
import com.mohamed.halim.essa.weather.data.DayForecast
import com.mohamed.halim.essa.weather.data.WeatherResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


class RemoteDataSource(var city: String,var unit : String) : DataSource {
    private val BASE_URL = "http://api.openweathermap.org/"
    private val apiService: ApiService by lazy {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

    override fun getWeatherData(): Flowable<List<DayForecast>> {
        return apiService.getFiveDaysWeather(city, unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { weatherResponse ->
                weatherResponse.daysForecast
            }
    }

}
