package com.mohamed.halim.essa.weather.data.local

import com.mohamed.halim.essa.weather.data.DataSource
import com.mohamed.halim.essa.weather.data.DayForecast
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class LocalDataSource(val weatherDao: WeatherDao) : DataSource{
    override fun getWeatherData(): Flowable<List<DayForecast>> {
        return weatherDao.getForecast()
    }


    fun cacheData(daysForecast  : List<DayForecast>){
        Timber.d(daysForecast.toString())
        Observable.fromCallable {
            weatherDao.insertAll(daysForecast)
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun clearData() {
        Observable.fromCallable {
            weatherDao.clear()
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

}