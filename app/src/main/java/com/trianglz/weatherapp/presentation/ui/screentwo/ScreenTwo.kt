package com.trianglz.weatherapp.presentation.ui.screentwo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScreenTwo(modifier: Modifier = Modifier) {
    val viewModel: ScreenTwoViewModel = hiltViewModel()

    /**
     * permission state is used to check for the notifications permission if is granted it shows the notification
     * and if not it asks for the permission first
     * */

    val notificationPermission =
        rememberPermissionState(
            permission = android.Manifest.permission.POST_NOTIFICATIONS
        )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (notificationPermission.status.isGranted) {
            Text(text = "Notification Permission Granted!")
        } else {
            Text(text = "Notification Permission NOT Granted!")

            Button(onClick = { notificationPermission.launchPermissionRequest() }) {
                Text(text = "Grant Notification Permission")
            }
        }


        Button(onClick = { viewModel.showNotification() }) {
            Text(text = "Show Notification")
        }


        val context = LocalContext.current
        Button(onClick = { viewModel.enqueueNotification(context.applicationContext) }) {
            Text(text = "Run Worker")
        }
    }
}