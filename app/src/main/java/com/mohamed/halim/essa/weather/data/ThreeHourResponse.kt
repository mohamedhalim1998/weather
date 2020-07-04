package com.mohamed.halim.essa.weather.data

import com.google.gson.annotations.SerializedName

data class ThreeHourResponse(
    @SerializedName("list") val daysForecast: List<ThreeHourForecast>,
    val city: City
)

data class City(
    val id: Int,
    val name: String,
    val timezone: Long,
    val sunrise: Long,
    val sunset: Long
)

data class ThreeHourForecast(
    @SerializedName("dt") val timeStamp: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind
) {
    data class Main(
        @SerializedName("temp") val temperature: Float,
        @SerializedName("feels_like") val feelsLike: Float,
        val min: Float,
        val max: Float,
        val pressure: Float,
        val humidity: Float
    )

    data class Weather(
        val id: Long,
        @SerializedName("main") val condition: String
    )

    data class Clouds(@SerializedName("all") val clouds: Int)
    data class Wind(val speed: Float, @SerializedName("deg") val degree: Int)
}