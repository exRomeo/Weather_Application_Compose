package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trianglz.weatherapp.presentation.models.weatherdetails.CurrentWeatherUiModel
import com.trianglz.weatherapp.presentation.ui.theme.lavender

@Composable
fun CurrentWeatherCard(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeatherUiModel ,
    containerColor: Color = Color.White.copy(alpha = 0.05f),
    contentColor: Color = lavender
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(Modifier) {
                        Icon(
                            imageVector = Icons.Rounded.LocationOn,
                            contentDescription = null
                        )
                        Text(
                            text = "${currentWeather.cityName}, ${currentWeather.countryCode}",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Text(
                        text = getStringForCurrentTemperature(currentWeather.currentTemperature),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnimation(
                        animation = currentWeather.icon,
                        modifier = Modifier.size(size = 150.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "H:${currentWeather.high}° L:${currentWeather.low}°",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "|", style = TextStyle(color = MaterialTheme.colorScheme.primaryContainer))
                Text(
                    modifier = Modifier.weight(1f),
                    text = currentWeather.description,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

private fun getStringForCurrentTemperature(currentTemperature: String): AnnotatedString {
    return buildAnnotatedString {
        val values = currentTemperature.split('.')
        withStyle(style = SpanStyle(fontSize = 88.sp)) { append(values[0]) }

        withStyle(style = SpanStyle(fontSize = 24.sp)) {
            append(".${values[1]}°")
        }
    }
}