package com.trianglz.weatherapp.presentation.ui.components


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.R
import com.trianglz.weatherapp.data.models.weather.WeatherDto
import com.trianglz.weatherapp.presentation.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.presentation.ui.theme.PurpleIndigoGradient
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.trianglz.weatherapp.presentation.ui.theme.lavender

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    currentTemperature: Int,
    highTemperature: Int,
    lowTemperature: Int,
    location: String,
    description: String,
    @DrawableRes icon: Int = R.drawable.sun_cloud_angled_rain,
) {
    Row(modifier = modifier
        .aspectRatio(342 / 184f)
        .graphicsLayer {
            shadowElevation = 5.dp.toPx()
            shape = SlantShape(50.dp.toPx(), 0.625f)
            clip = false
        }
        .drawBehind {
            drawPath(
                getSlantPath(size, 50.dp.toPx(), 0.625f),
                brush = PurpleIndigoGradient,
                style = Fill
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .padding(20.dp)
                .weight(1f), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$currentTemperature°",
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier,
                maxLines = 1,
                overflow = TextOverflow.Visible
            )

            Column {
                Text(
                    text = "H:$highTemperature° L:$lowTemperature°",
                    style = MaterialTheme.typography.bodyMedium.copy(color = lavender),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = location,
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .offset(y = (-20).dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                painter = painterResource(id = icon),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = description,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(
    device = "spec:parent=pixel_4",
    showSystemUi = true,
    showBackground = true
)
@Composable
fun WeatherCardPreview() {
    WeatherAppTheme {

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            WeatherCard(
                currentTemperature = 2300000,
                highTemperature = 260000000,
                lowTemperature = 800000000,
                location = "Montreal, Canada, Canada, Canada, Canada, Canada, Canada, Canada, Canada, Canada, Canada",
                description = "Partly Cloudy, and there is a good chance of rain so wear something water proof",
                icon = R.drawable.sun_cloud_angled_rain,
                modifier = Modifier

            )
        }
    }
}

@Preview(
    device = "spec:width=411dp,height=891dp",
    showSystemUi = true,
    showBackground = true
)
@Composable
fun WeatherListPreview() {
    WeatherAppTheme {


        listOf(
            WeatherDto(
                cloudPercentage = 15,
                currentTemperature = 300000000,
                feelsLike = 55,
                humidityPercentage = 93,
                lowTemperature = 300000000,
                highTemperature = 300000000,
                windSpeed = 13.2,
                windDegree = 210,
                sunrise = 562165165163,
                sunset = 315656565656
            ),
            WeatherDto(
                cloudPercentage = 15,
                currentTemperature = 30,
                feelsLike = 55,
                humidityPercentage = 93,
                lowTemperature = 10,
                highTemperature = 300,
                windSpeed = 13.2,
                windDegree = 210,
                sunrise = 562165165163,
                sunset = 315656565656
            ),
            WeatherDto(
                cloudPercentage = 15,
                currentTemperature = 30,
                feelsLike = 55,
                humidityPercentage = 93,
                lowTemperature = 10,
                highTemperature = 300,
                windSpeed = 13.2,
                windDegree = 210,
                sunrise = 562165165163,
                sunset = 315656565656
            ),
            WeatherDto(
                cloudPercentage = 15,
                currentTemperature = 30,
                feelsLike = 55,
                humidityPercentage = 93,
                lowTemperature = 10,
                highTemperature = 300,
                windSpeed = 13.2,
                windDegree = 210,
                sunrise = 562165165163,
                sunset = 315656565656
            ),
            WeatherDto(
                cloudPercentage = 15,
                currentTemperature = 30,
                feelsLike = 55,
                humidityPercentage = 93,
                lowTemperature = 10,
                highTemperature = 300,
                windSpeed = 13.2,
                windDegree = 210,
                sunrise = 562165165163,
                sunset = 315656565656
            ),
        )
        LazyColumn(
            modifier = Modifier.background(
                brush = BackgroundGradient
            ),
            contentPadding = PaddingValues(
                vertical = 10.dp,
                horizontal = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(5) {
                WeatherCard(
                    currentTemperature = 200000000,
                    highTemperature = 300000000,
                    lowTemperature = 100000000,
                    location = "Montreal, Canada, Canada, Canada, Canada, Canada, Canada, Canada, Canada, Canada, Canada",
                    description = "Partly Cloudy, and there is a good chance of rain so wear something water proof"
                )
            }

        }
    }
}