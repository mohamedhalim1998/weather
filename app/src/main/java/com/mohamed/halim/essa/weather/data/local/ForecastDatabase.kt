package com.mohamed.halim.essa.weather.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohamed.halim.essa.weather.data.DayForecast
import com.mohamed.halim.essa.weather.utils.RoomTypeConverters

@Database(entities = [DayForecast::class], version = 3, exportSchema = false)
@TypeConverters(RoomTypeConverters::class)
abstract class ForecastDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: ForecastDatabase? = null
        fun getInstance(context: Context): ForecastDatabase {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ForecastDatabase::class.java,
                    "forecast_database"
                ).fallbackToDestructiveMigration().build()
            }
            INSTANCE = instance
            return instance
        }
    }
}