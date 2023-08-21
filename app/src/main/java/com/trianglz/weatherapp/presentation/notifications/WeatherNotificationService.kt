package com.trianglz.weatherapp.presentation.notifications

import android.app.NotificationManager
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.trianglz.weatherapp.R
import javax.inject.Inject
import kotlin.random.Random


const val NotificationID = "weather_notification"

class WeatherNotificationService @Inject constructor(
    private val notificationManager: NotificationManager,
    private val notificationBuilder: NotificationCompat.Builder
) {

    fun showNotification(
        title: String = "Test",
        message: String = "Testing Notifications",
        @DrawableRes icon: Int = R.drawable.tornado
    ) {
        val notification = notificationBuilder
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(icon)
            .setPriority(
                NotificationManager
                    .IMPORTANCE_HIGH
            )
            .setAutoCancel(true)
            .build()

        notificationManager.notify(Random.nextInt(), notification)
    }
}