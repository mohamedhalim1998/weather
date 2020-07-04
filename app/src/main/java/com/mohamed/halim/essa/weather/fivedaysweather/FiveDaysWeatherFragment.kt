package com.mohamed.halim.essa.weather.fivedaysweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.halim.essa.weather.R
import com.mohamed.halim.essa.weather.data.DayForecast
import com.mohamed.halim.essa.weather.data.remote.RemoteDataSource
import com.mohamed.halim.essa.weather.databinding.FiveDaysWeatherBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.five_days_weather.*
import timber.log.Timber

class FiveDaysWeatherFragment : Fragment() {
    lateinit var binding: FiveDaysWeatherBinding
    lateinit var adapter: WeatherAdapter
    lateinit var viewModel: WeatherViewModel
    lateinit var disposables : CompositeDisposable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.five_days_weather, container, false);
        binding.lifecycleOwner = this
        disposables = CompositeDisposable()
        val factory = WeatherViewModelFactory(RemoteDataSource())
        viewModel = ViewModelProvider(this, factory).get(WeatherViewModel::class.java)
        initRecycleView()
        return binding.root
    }

    private fun initRecycleView() {
        adapter = WeatherAdapter()
        viewModel.weatherDate.subscribe(object : SingleObserver<List<DayForecast>> {
            override fun onSuccess(t: List<DayForecast>?) {
                Timber.d("get the list" + t?.size)
                adapter.submitList(t)
            }

            override fun onSubscribe(d: Disposable?) {
                disposables.add(d)
            }

            override fun onError(e: Throwable?) {
                Timber.e(e)
            }

        })
        val manager = LinearLayoutManager(requireContext())
        binding.weatherList.adapter = adapter
        binding.weatherList.layoutManager = manager
    }
}