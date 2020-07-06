package com.mohamed.halim.essa.weather.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mohamed.halim.essa.weather.R
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("temperature")
fun setTemperature(textView: TextView, temp: Float) {
    textView.text = textView.context.getString(R.string.temperature, temp.toInt())
}

@BindingAdapter("temperatureFeelsLike")
fun setTemperatureFeelsLike(textView: TextView, temp: Float) {
    textView.text = textView.context.getString(R.string.feels_like, temp.toInt())
}

@BindingAdapter("date")
fun setDate(textView: TextView, timeStamp: Long) {
    val dateFormat = SimpleDateFormat("MMM d, yyyy")
    val date = Date(timeStamp * 1000)
    textView.text = dateFormat.format(date)
}

@BindingAdapter("temperatureMin")
fun setTemperatureMin(textView: TextView, temp: Float) {
    textView.text = textView.context.getString(R.string.min, temp.toInt())
}

@BindingAdapter("temperatureMax")
fun setTemperatureMax(textView: TextView, temp: Float) {
    textView.text = textView.context.getString(R.string.max, temp.toInt())
}

@BindingAdapter("conditionIcon")
fun setConditionIcon(imageView: ImageView, condition: Int) {
    imageView.setImageDrawable(
        imageView.context.getDrawable(
            when (condition) {
                in 200..232 -> R.drawable.thunderstorm
                in 300..321 -> R.drawable.heavy_rain
                in 500..503 -> R.drawable.light_rain
                in 521..531 -> R.drawable.heavy_rain
                in 600..622 -> R.drawable.snow
                in 700..781 -> R.drawable.heavy_cloud
                800 -> R.drawable.clear
                in 801..804 -> R.drawable.light_cloud
                else -> R.drawable.clear
            }
        )
    )
}

@BindingAdapter("numberText")
fun numberText(textView: TextView, v: Int) {
    textView.text = v.toString()
}

@BindingAdapter("numberText")
fun numberText(textView: TextView, v: Float) {
    textView.text = v.toString()
}

@BindingAdapter("timeText")
fun timeText(textView: TextView, time: Long) {
    val dateFormat = SimpleDateFormat("h:mm a")
    val date = Date(time*1000)
    textView.text = dateFormat.format(date)
}

@BindingAdapter("percentText")
fun percentText(textView: TextView, percent: Int) {
    textView.text = "$percent %"
}