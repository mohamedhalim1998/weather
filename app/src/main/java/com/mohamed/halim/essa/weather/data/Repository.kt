package com.mohamed.halim.essa.weather.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.mohamed.halim.essa.weather.data.local.LocalDataSource
import com.mohamed.halim.essa.weather.data.remote.RemoteDataSource
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import timber.log.Timber

class Repository(val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) {
    fun getWeatherData(): LiveData<List<DayForecast>> {
        remoteDataSource.getWeatherData().onErrorReturn {
            emptyList()
        }.doOnNext {
            if (it.isNotEmpty()) {
                localDataSource.clearData()
                localDataSource.cacheData(it)
            }
        }.subscribe()

        val data: LiveData<List<DayForecast>> = LiveDataReactiveStreams.fromPublisher(
            localDataSource.getWeatherData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
        return data
    }

    fun getDayForecast(date: Long): LiveData<DayForecast> {
        return LiveDataReactiveStreams.fromPublisher(
            localDataSource.getDayForecast(date).subscribeOn(Schedulers.io()).toFlowable()
        )
    }
}