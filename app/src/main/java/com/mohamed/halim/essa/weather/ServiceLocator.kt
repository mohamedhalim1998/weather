package com.mohamed.halim.essa.weather

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.mohamed.halim.essa.weather.data.Repository
import com.mohamed.halim.essa.weather.data.local.ForecastDatabase
import com.mohamed.halim.essa.weather.data.local.LocalDataSource
import com.mohamed.halim.essa.weather.data.remote.RemoteDataSource

object ServiceLocator {

    @Volatile
    private var DATABASE: ForecastDatabase? = null

    @Volatile
    private var repository: Repository? = null

    fun provideRepo(context: Context): Repository {
        synchronized(this) {
            return repository ?: createRepo(context)
        }
    }

    private fun createRepo(context: Context): Repository {
        val remoteDataSource = createRemoteDataSource(context)
        val localDataSource = createLocalDataSource(context)
        return Repository(localDataSource, remoteDataSource)
    }

    private fun createLocalDataSource(context: Context): LocalDataSource {
        val database = DATABASE ?: createDatabase(context)
        return LocalDataSource(database.weatherDao)
    }

    private fun createDatabase(context: Context): ForecastDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ForecastDatabase::class.java,
            "forecast_database"
        ).fallbackToDestructiveMigration().build()
    }

    private fun createRemoteDataSource(context: Context): RemoteDataSource {
        val city = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(context.getString(R.string.pref_city_key), "Cairo")!!
        val unit = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(context.getString(R.string.pref_unit_key), "metric")!!

        return RemoteDataSource(city, unit)
    }
}