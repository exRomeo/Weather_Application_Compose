package com.trianglz.weatherapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.ui.theme.WeatherAppTheme
import com.trianglz.weatherapp.ui.theme.lavender

@Composable
fun WeatherSearchBar(
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    text: String = "",
    onTextChanged: (String) -> Unit = {}
) {

    var inFocus by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val elevation = if (inFocus) 30.dp else 0.dp
    val animatedElevation by animateDpAsState(
        targetValue = elevation,
        animationSpec = tween(150, easing = EaseIn),
        label = "animatedElevation"
    )
    val alphaValue = if (inFocus) 1f else 0.5f
    val animatedAlpha by animateFloatAsState(
        targetValue = alphaValue,
        animationSpec = tween(150, easing = EaseIn),
        label = "animatedAlpha"
    )
    val searchBarShape = RoundedCornerShape(10.dp)
    Card(
        modifier = modifier
            .shadow(
                elevation = animatedElevation,
                ambientColor = Color.Black,
                spotColor = Color.Black,
                shape = searchBarShape
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = animatedElevation),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { state -> inFocus = state.isFocused },
            value = text,
            onValueChange = onTextChanged,
            textStyle = TextStyle(color = lavender),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = lavender.copy(alpha = animatedAlpha)
                )
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = inFocus,
                    enter = fadeIn(animationSpec = tween(150, easing = EaseIn)),
                    exit = fadeOut(animationSpec = tween(150, easing = EaseIn))
                )
                {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty())
                                onTextChanged("")
                            else
                                focusManager.clearFocus()
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

            },
            placeholder = {
                Text(
                    text = placeHolder,
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }, shape = searchBarShape,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.onSurface,
                errorCursorColor = MaterialTheme.colorScheme.onError,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            )

        )
    }
}




@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_4")
@Composable
fun WeatherSearchBarPreview() {
    WeatherAppTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(BackgroundGradient)
        ) {
            WeatherSearchBar(
                modifier = Modifier.padding(8.dp),
                placeHolder = "Search For A Country..."
            )
        }
    }
}

