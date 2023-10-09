package com.trianglz.weatherapp.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.trianglz.weatherapp.presentation.ui.theme.lavender

/**
 * [MessageScreen] a screen with a message to the user in the middle
 * @param message represents the Message
 * */

@Composable
fun MessageScreen(modifier: Modifier = Modifier, @StringRes message: Int) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.9f),
            text = stringResource(id = message),
            style = MaterialTheme.typography.headlineSmall.copy(
                color = lavender,
                textAlign = TextAlign.Center
            )
        )
    }
}