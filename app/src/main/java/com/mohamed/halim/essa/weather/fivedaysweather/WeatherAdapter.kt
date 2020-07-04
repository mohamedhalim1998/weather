package com.mohamed.halim.essa.weather.fivedaysweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.halim.essa.weather.data.DayForecast
import com.mohamed.halim.essa.weather.databinding.ListItemBinding
import timber.log.Timber


class WeatherAdapter : ListAdapter<DayForecast, WeatherViewHolder>(DayForecastDiffCallBacks()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        Timber.d(getItem(position).timeStamp.toString())
        holder.bind(getItem(position))
    }

}

class DayForecastDiffCallBacks : DiffUtil.ItemCallback<DayForecast>() {
    override fun areItemsTheSame(oldItem: DayForecast, newItem: DayForecast): Boolean {
        return oldItem.timeStamp == newItem.timeStamp
    }

    override fun areContentsTheSame(oldItem: DayForecast, newItem: DayForecast): Boolean {
        return oldItem == newItem
    }

}

class WeatherViewHolder private constructor(val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(forecast: DayForecast) {
        binding.forecast = forecast
    }

    companion object {
        fun from(parent: ViewGroup): WeatherViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(layoutInflater, parent, false)
            return WeatherViewHolder(binding)
        }
    }

}
