package com.trianglz.weatherapp

import android.app.Application
import com.trianglz.weatherapp.presentation.notifications.NotificationsUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()

       NotificationsUtils.setupNotificationChannel(this)
    }
}