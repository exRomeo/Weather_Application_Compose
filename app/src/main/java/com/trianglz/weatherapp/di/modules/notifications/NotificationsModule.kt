package com.trianglz.weatherapp.di.modules.notifications

import android.app.Application
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.trianglz.weatherapp.presentation.notifications.NotificationID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationsModule {

    @Provides
    @Singleton
    fun provideNotificationManager(
        application: Application
    ): NotificationManager =
        application
            .getSystemService(
                NotificationManager::class.java
            )

    @Provides
    @Singleton
    fun provideNotificationBuilder(
        application: Application
    ): NotificationCompat.Builder =
        NotificationCompat.Builder(application, NotificationID)

}