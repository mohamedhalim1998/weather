package com.mohamed.halim.essa.weather.todayweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mohamed.halim.essa.weather.R
import com.mohamed.halim.essa.weather.data.Repository
import com.mohamed.halim.essa.weather.data.local.ForecastDatabase
import com.mohamed.halim.essa.weather.data.local.LocalDataSource
import com.mohamed.halim.essa.weather.data.remote.RemoteDataSource
import com.mohamed.halim.essa.weather.databinding.FragmentTodayWeatherBinding
import kotlinx.android.synthetic.main.fragment_today_weather.*
import timber.log.Timber


class TodayWeatherFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentTodayWeatherBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_today_weather, container, false)
        binding.lifecycleOwner = this
        val database = ForecastDatabase.getInstance(requireContext())
        val repository = Repository(LocalDataSource(database.weatherDao), RemoteDataSource())
        val arguments = TodayWeatherFragmentArgs.fromBundle(requireArguments())
        val factory = TodayWeatherViewModelFactory(arguments.date, repository)
        val viewModel = ViewModelProvider(this, factory).get(TodayWeatherViewModel::class.java)
        viewModel.forecast.observe(viewLifecycleOwner, Observer {
            binding.forecast = it
        })
        return binding.root
    }

}