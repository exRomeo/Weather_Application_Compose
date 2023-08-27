package com.trianglz.weatherapp.presentation.ui.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun AnimateAppearanceRTL(
    enter: EnterTransition = slideInHorizontally(initialOffsetX = { it }) + fadeIn(initialAlpha = 0.3f),
    content: @Composable () -> Unit
) {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = state,
        enter = enter
    ) { content() }
}