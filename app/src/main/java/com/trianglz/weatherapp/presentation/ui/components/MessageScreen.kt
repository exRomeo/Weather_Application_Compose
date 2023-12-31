package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.trianglz.weatherapp.presentation.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.presentation.ui.theme.lavender

/**
 * [MessageScreen] a screen with a message to the user in the middle
 * @param message represents the Message
 * */

@Composable
fun MessageScreen(modifier: Modifier = Modifier, message: String) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.9f),
            text = message,
            style = MaterialTheme.typography.headlineSmall.copy(
                color = lavender,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Preview
@Composable
fun MessageScreenPreview() {
    MessageScreen(
        modifier = Modifier.background(BackgroundGradient),
        "Something Went Wrong, Please Try Again Later and if the problem persists please contact the developers"
    )
}