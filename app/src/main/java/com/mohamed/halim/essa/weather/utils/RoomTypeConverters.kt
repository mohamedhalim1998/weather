package com.mohamed.halim.essa.weather.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mohamed.halim.essa.weather.data.Weather

class RoomTypeConverters {

    @TypeConverter
    fun listToJson(value: List<Weather>): String {
        return Gson().toJson(value).toString()
    }

    @TypeConverter
    fun jsonToList(value: String): List<Weather> {
        return Gson().fromJson(value, Array<Weather>::class.java).toList()

    }
}