package com.trianglz.weatherapp.presentation.ui.screentwo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.trianglz.weatherapp.presentation.notifications.NotificationsWorker
import com.trianglz.weatherapp.presentation.notifications.WeatherNotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ScreenTwoViewModel
@Inject constructor(
    private val weatherNotificationService: WeatherNotificationService
) : ViewModel() {


    fun showNotification() {
        weatherNotificationService.showNotification()
    }



    /**
     * [enqueueNotification] creates a work request using a Builder to run the worker each 15 minutes
     * which is the minimum allowed interval for periodic work requests
     * then put the request to the queue waiting for its time to run
     * */

    fun enqueueNotification(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<NotificationsWorker>(
            15, TimeUnit.MINUTES
        )
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "NotificationWorker",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}

