package com.mohamed.halim.essa.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mohamed.halim.essa.weather.data.DayForecast
import io.reactivex.Flowable

@Dao
interface WeatherDao {
    @Insert
    fun insertAll(daysForecast: List<DayForecast>)

    @Query("SELECT * FROM day_forecast")
    fun getForecast(): Flowable<List<DayForecast>>

    @Query("DELETE FROM day_forecast")
    fun clear()
}