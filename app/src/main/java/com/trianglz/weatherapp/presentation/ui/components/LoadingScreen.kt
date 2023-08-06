package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.presentation.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.presentation.ui.theme.darkPurple


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.then(Modifier.size(65.dp)),
            strokeCap = StrokeCap.Round,
            strokeWidth = 8.dp, trackColor = darkPurple
        )
    }
}

@Preview(device = "id:pixel_7_pro", showSystemUi = true, showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen(modifier = Modifier.drawBehind { drawRect(BackgroundGradient) })
}