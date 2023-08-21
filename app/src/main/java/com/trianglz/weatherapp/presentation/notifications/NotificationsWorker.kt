package com.trianglz.weatherapp.presentation.notifications

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationsWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    /**
     * [doWork] gets executed when the scheduled work is activated
     * and it shows a test notification
     * */
    override fun doWork(): Result {

        val notificationService = WeatherNotificationService(
            applicationContext.getSystemService(NotificationManager::class.java),
            NotificationCompat.Builder(applicationContext, NotificationID)
        )
        NotificationsUtils.setupNotificationChannel(applicationContext)

        notificationService.showNotification()
        return Result.success()
    }
}