package com.trianglz.weatherapp.presentation.extensions

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

/**
 * @param ratio is the percentage of screen's height the component's maximum height will be
 * */

fun Modifier.maxHeightScreenRatio(@FloatRange(0.2, 1.0) ratio: Float) = composed {
    this.heightIn(max = (LocalConfiguration.current.screenHeightDp * ratio).dp)
}

