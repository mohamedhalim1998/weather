package com.mohamed.halim.essa.weather.data

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.mohamed.halim.essa.weather.utils.RoomTypeConverters

data class WeatherResponse(
    @SerializedName("list") val daysForecast: List<DayForecast>,
    val city: City
) {
    data class City(
        val id: Int,
        val name: String,
        val timezone: Long
    )
}

@Entity(tableName = "day_forecast")
data class DayForecast(
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("dt")
    @PrimaryKey val timeStamp: Long,
    @SerializedName("feels_like")
    @Embedded val feelsLike: FeelsLike,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("speed")
    val speed: Float,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
    @SerializedName("temp")
    @Embedded val temperatures: Temperatures,
    @SerializedName("weather")
    @TypeConverters(RoomTypeConverters::class)
    val weather: List<Weather>
)

data class FeelsLike(
    @SerializedName("day")
    @ColumnInfo(name = "feels_like_day") val day: Float,
    @SerializedName("eve")
    @ColumnInfo(name = "feels_like_eve") val eve: Float,
    @SerializedName("morn")
    @ColumnInfo(name = "feels_like_morn") val morn: Float,
    @SerializedName("night")
    @ColumnInfo(name = "feels_like_night") val night: Float
)

data class Temperatures(
    @SerializedName("day")
    val day: Float,
    @SerializedName("eve")
    val eve: Float,
    @SerializedName("max")
    val max: Float,
    @SerializedName("min")
    val min: Float,
    @SerializedName("morn")
    val morn: Float,
    @SerializedName("night")
    val night: Float
)

data class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)