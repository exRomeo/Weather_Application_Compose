package com.trianglz.weatherapp.presentation.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

object NotificationsUtils {

    /**
     * Register a notification channel to be use to send notifications
     * */


    fun setupNotificationChannel(context: Context) {
        val notificationChannel = NotificationChannel(
            NotificationID, "Weather", NotificationManager.IMPORTANCE_HIGH
        )

        notificationChannel.description = "Experimental Notification Channel"

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)
    }
}