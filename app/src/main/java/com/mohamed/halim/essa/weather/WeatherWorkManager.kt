package com.mohamed.halim.essa.weather

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class WeatherWorkManager(val context: Context, param: WorkerParameters) :
    Worker(context.applicationContext, param) {
    companion object {
        val WORK_NAME = "com.mohamed.halim.essa.weather.WeatherWorkManager"
    }

    override fun doWork(): Result {
        val repo = ServiceLocator.provideRepo(context.applicationContext)
        repo.refreshData()
        return Result.success()
    }


}