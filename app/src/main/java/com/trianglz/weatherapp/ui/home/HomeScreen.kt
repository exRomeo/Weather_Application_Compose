package com.trianglz.weatherapp.ui.home

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.data.weather.Weather
import com.trianglz.weatherapp.ui.components.WeatherCard
import com.trianglz.weatherapp.ui.components.WeatherSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier, containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = null,
                    )
                },
                title = {
                    Text(
                        text = "Weather",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = null,
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)

        ) {
            var text by remember { mutableStateOf("") }
            WeatherSearchBar(
                modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp),
                placeHolder = "Search for a Country",
                text = text,
                onTextChanged = { newValue -> text = newValue })
            WeatherDataList()
        }
    }
}


@Composable
fun WeatherDataList(
    modifier: Modifier = Modifier,
    weatherData: List<Weather> = Weather.getDummyData()
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = spacedBy(16.dp)
    ) {
        items(weatherData, key = { it.sunrise }) {
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


