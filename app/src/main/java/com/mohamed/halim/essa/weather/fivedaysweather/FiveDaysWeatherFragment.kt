package com.mohamed.halim.essa.weather.fivedaysweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.halim.essa.weather.R
import com.mohamed.halim.essa.weather.data.DayForecast
import com.mohamed.halim.essa.weather.data.Repository
import com.mohamed.halim.essa.weather.data.local.ForecastDatabase
import com.mohamed.halim.essa.weather.data.local.LocalDataSource
import com.mohamed.halim.essa.weather.data.remote.RemoteDataSource
import com.mohamed.halim.essa.weather.databinding.FiveDaysWeatherBinding
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber


class FiveDaysWeatherFragment : Fragment() {
    lateinit var binding: FiveDaysWeatherBinding
    lateinit var adapter: WeatherAdapter
    lateinit var viewModel: WeatherViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.five_days_weather, container, false)
        binding.lifecycleOwner = this
        val database = ForecastDatabase.getInstance(requireContext())
        val repository = Repository(LocalDataSource(database.weatherDao), RemoteDataSource())
        val factory = WeatherViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(WeatherViewModel::class.java)
        initRecycleView()
        setupObservers()
        return binding.root
    }

    private fun initRecycleView() {
        adapter = WeatherAdapter(DayClickListener {
        })
        val manager = LinearLayoutManager(requireContext())
        binding.weatherList.adapter = adapter
        binding.weatherList.layoutManager = manager
    }

    private fun setupObservers() {
        viewModel.weatherDate.observe(viewLifecycleOwner, Observer {
            Timber.d(it.toString())
            adapter.submitList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}