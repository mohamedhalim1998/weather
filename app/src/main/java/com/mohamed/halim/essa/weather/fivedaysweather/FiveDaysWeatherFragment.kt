package com.mohamed.halim.essa.weather.fivedaysweather

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.halim.essa.weather.R
import com.mohamed.halim.essa.weather.ServiceLocator
import com.mohamed.halim.essa.weather.data.DayForecast
import com.mohamed.halim.essa.weather.data.Repository
import com.mohamed.halim.essa.weather.data.local.ForecastDatabase
import com.mohamed.halim.essa.weather.data.local.LocalDataSource
import com.mohamed.halim.essa.weather.data.remote.RemoteDataSource
import com.mohamed.halim.essa.weather.databinding.FiveDaysWeatherBinding
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber


class FiveDaysWeatherFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {
    lateinit var binding: FiveDaysWeatherBinding
    lateinit var adapter: WeatherAdapter
    lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.five_days_weather, container, false)
        binding.lifecycleOwner = this
        val repository = ServiceLocator.provideRepo(requireContext())
        val factory = WeatherViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(WeatherViewModel::class.java)
        initRecycleView()
        setupObservers()
        return binding.root
    }

    private fun initRecycleView() {
        adapter = WeatherAdapter(DayClickListener {
            viewModel.onDayClick(it)
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

        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(
                    FiveDaysWeatherFragmentDirections.actionFiveDaysWeatherToTodayWeatherFragment(
                        it
                    )
                )
                viewModel.doneNavigating()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.preference -> {
                findNavController().navigate(FiveDaysWeatherFragmentDirections.actionFiveDaysWeatherToPreferenceFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .unregisterOnSharedPreferenceChangeListener(this)
        super.onDestroy()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == getString(R.string.pref_city_key)) {
            val city = sharedPreferences?.getString(key, "Cairo") ?: "Cairo"
            viewModel.changeCity(city)
        } else if (key == getString(R.string.pref_unit_key)) {
            val unit = sharedPreferences?.getString(key, "metric") ?: "metric"
            viewModel.changeUnit(unit)
        }
    }


}