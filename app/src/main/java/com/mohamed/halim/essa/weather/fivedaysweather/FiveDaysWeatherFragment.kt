package com.mohamed.halim.essa.weather.fivedaysweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohamed.halim.essa.weather.R
import com.mohamed.halim.essa.weather.data.DayForecast
import com.mohamed.halim.essa.weather.data.remote.RemoteDataSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.five_days_weather.*

class FiveDaysWeatherFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.five_days_weather, container, false)
        val source = RemoteDataSource()
        source.getWeatherData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<DayForecast>> {
                override fun onSuccess(t: List<DayForecast>?) {
                    text.text = t.toString()
                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onError(e: Throwable?) {
                    text.text = e.toString()
                }


            })
        return view
    }
}