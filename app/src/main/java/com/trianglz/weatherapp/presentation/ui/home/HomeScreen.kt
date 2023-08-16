package com.trianglz.weatherapp.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.presentation.extensions.getWeatherDescription
import com.trianglz.weatherapp.presentation.extensions.getWeatherIcon
import com.trianglz.weatherapp.presentation.ui.components.LoadingScreen
import com.trianglz.weatherapp.presentation.ui.components.MessageScreen
import com.trianglz.weatherapp.presentation.ui.components.WeatherCard
import com.trianglz.weatherapp.presentation.ui.components.WeatherSearchBar
import com.trianglz.weatherapp.presentation.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.trianglz.weatherapp.presentation.viewcontract.UIAction
import com.trianglz.weatherapp.presentation.viewcontract.UIEvent
import com.trianglz.weatherapp.presentation.viewcontract.UIState
import kotlinx.coroutines.flow.SharedFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, openDrawer: () -> Unit = {}) {
    val viewModel: HomeViewModel = hiltViewModel()
    val snackbarHostState = remember { SnackbarHostState() }
    EventProcessor(snackbarHostState = snackbarHostState, uiEvents = viewModel.uiEvents)

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    },
        modifier = modifier,
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { openDrawer() },
                        imageVector = Icons.Rounded.Menu,
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
    ) { paddingValues ->
        val text by viewModel.searchTextState.collectAsState()
        val searchState = viewModel.searchBarState
        val uiState by viewModel.homeUIState.collectAsState()
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            HomeScreenContent(modifier = Modifier.padding(top = 56.dp), uiState = uiState)

            WeatherSearchBar(
                modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp),
                searchBarState = searchState,
                onItemClicked = { country ->
                    viewModel.performAction(
                        UIAction.ItemSelected(
                            country,
                            5
                        )
                    )
                },
                onTextChanged = { newValue ->
                    viewModel.performAction(
                        UIAction.SearchTextChanged(
                            newValue
                        )
                    )
                },
                text = text
            )
        }
    }
}

@Composable
fun HomeScreenContent(modifier: Modifier = Modifier, uiState: UIState<List<WeatherDomainModel>>) {
    when (uiState) {
        is UIState.Idle -> MessageScreen(
            modifier = modifier,
            message = "Start searching for a country to display weather details of its top 5 cities"
        )

        is UIState.Loading -> LoadingScreen(modifier = modifier)

        is UIState.Success -> WeatherDataList(modifier = modifier, weatherData = uiState.list)

        is UIState.Failure -> MessageScreen(modifier = modifier, message = uiState.message)
    }
}


@Composable
fun WeatherDataList(
    modifier: Modifier = Modifier,
    weatherData: List<WeatherDomainModel>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = spacedBy(20.dp)
    ) {
        items(weatherData) { weather ->
            WeatherCard(
                currentTemperature = weather.currentTemperature,
                highTemperature = weather.highTemperature,
                lowTemperature = weather.lowTemperature,
                location = "${weather.cityName}, ${weather.countryCode}",
                description = weather.getWeatherDescription(),
                icon = weather.getWeatherIcon()
            )
        }
    }
}

@Composable
fun EventProcessor(snackbarHostState: SnackbarHostState, uiEvents: SharedFlow<UIEvent>) {
    LaunchedEffect(Unit) {
        uiEvents.collect { uiEvent ->
            when (uiEvent) {
                is UIEvent.Message -> snackbarHostState.showSnackbar(
                    message = uiEvent.message,
                    duration = SnackbarDuration.Long
                )
            }
        }
    }
}


@Preview(device = "id:pixel_4", showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    WeatherAppTheme {
        HomeScreen(modifier = Modifier.background(BackgroundGradient))
    }
}
