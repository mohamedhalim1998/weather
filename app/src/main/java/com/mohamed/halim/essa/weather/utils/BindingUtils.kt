package com.mohamed.halim.essa.weather.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mohamed.halim.essa.weather.R
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setTemperature")
fun setTemperature(textView: TextView, temp: Float) {
    textView.text = textView.context.getString(R.string.temperature, temp.toInt())
}

@BindingAdapter("setTemperatureFeelsLike")
fun setTemperatureFeelsLike(textView: TextView, temp: Float) {
    textView.text = textView.context.getString(R.string.feels_like, temp.toInt())
}

@BindingAdapter("setDate")
fun setDate(textView: TextView, timeStamp: Long) {
    val dateFormat = SimpleDateFormat("MMM d, yyyy")
    val date = Date(timeStamp * 1000)
    textView.text = dateFormat.format(date)
}

@BindingAdapter("setTemperatureMin")
fun setTemperatureMin(textView: TextView, temp: Float) {
    textView.text = textView.context.getString(R.string.min, temp.toInt())
}

@BindingAdapter("setTemperatureMax")
fun setTemperatureMax(textView: TextView, temp: Float) {
    textView.text = textView.context.getString(R.string.max, temp.toInt())
}