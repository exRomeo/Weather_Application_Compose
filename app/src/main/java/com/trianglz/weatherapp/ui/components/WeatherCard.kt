package com.trianglz.weatherapp.ui.components


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.R
import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.ui.theme.WeatherAppTheme
import com.trianglz.weatherapp.ui.theme.lavender

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
    Box(modifier = modifier.aspectRatio(1.9546297f)) {
        Image(
            painter = painterResource(
                id = R.drawable.rectangle
            ),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Column(
            Modifier
                .fillMaxHeight()
                .padding(20.dp)
                .align(Alignment.TopStart), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$currentTemperature°",
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.heightIn(16.dp))
            Column {
                Text(
                    text = "H:$highTemperature° L:$lowTemperature°",
                    style = MaterialTheme.typography.bodyMedium.copy(color = lavender)
                )
                Text(
                    text = location,
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground)
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.TopEnd),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .offset(y = (-10).dp)
                    .fillMaxWidth(0.4f),
                painter = painterResource(id = icon),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = description,
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
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
                currentTemperature = 23,
                highTemperature = 26,
                lowTemperature = 8,
                location = "Montreal, Canada",
                description = "Partly Cloudy",
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


        val weatherList = listOf(
            Weather(
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
            Weather(
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
            Weather(
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
            Weather(
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
            Weather(
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
            items(weatherList) {
                WeatherCard(
                    currentTemperature = it.currentTemperature,
                    highTemperature = it.highTemperature,
                    lowTemperature = it.lowTemperature,
                    location = it.sunrise.toString(),
                    description = it.windDegree.toString()
                )
            }

        }
    }
}