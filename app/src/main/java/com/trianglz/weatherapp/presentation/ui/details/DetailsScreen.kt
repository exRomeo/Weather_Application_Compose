package com.trianglz.weatherapp.presentation.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.trianglz.weatherapp.presentation.models.weathercard.WeatherUiModel
import com.trianglz.weatherapp.presentation.models.weatherdetails.WeatherDetailsUiModel
import com.trianglz.weatherapp.presentation.navigation.appwide.NavigationActions
import com.trianglz.weatherapp.presentation.ui.components.CurrentWeatherCard
import com.trianglz.weatherapp.presentation.ui.components.DailyForecastCard
import com.trianglz.weatherapp.presentation.ui.components.ExtraDetailsCard
import com.trianglz.weatherapp.presentation.ui.components.HourlyForecastCard
import com.trianglz.weatherapp.presentation.ui.components.LoadingScreen
import com.trianglz.weatherapp.presentation.ui.components.MessageScreen
import com.trianglz.weatherapp.presentation.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.trianglz.weatherapp.presentation.viewcontract.UIState


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    navigationActions: NavigationActions,
    weather: WeatherUiModel
) {
    val viewModel: DetailsViewModel = hiltViewModel()
    viewModel.getWeatherDetails(weather)
    val uiState: UIState<WeatherDetailsUiModel> by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(onClick = navigationActions.popup) {
                        Icon(
                            modifier = Modifier,
                            imageVector = Icons.Rounded.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "${weather.cityName}, ${weather.countryCode}",
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        },
    ) { paddingValues ->
        val refreshState = rememberPullRefreshState(viewModel.refreshing, viewModel::refresh)
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .pullRefresh(refreshState)
        ) {
            when (val state = uiState) {
                is UIState.Success -> DetailsContent(weatherDetails = state.data)
                is UIState.Failure -> MessageScreen(message = state.message)
                is UIState.Loading -> LoadingScreen()
                is UIState.Idle -> {}
            }
            PullRefreshIndicator(viewModel.refreshing, refreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}

@Composable
fun DetailsContent(modifier: Modifier = Modifier, weatherDetails: WeatherDetailsUiModel) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = spacedBy(16.dp)
    ) {

        item {
            CurrentWeatherCard(currentWeather = weatherDetails.currentWeatherDetails)
        }

        item {
            HourlyForecastCard(hourlyList = weatherDetails.hourlyList)
        }

        item {
            ExtraDetailsCard(list = weatherDetails.extraDetailsList)
        }

        item {
            DailyForecastCard(dailyList = weatherDetails.dailyList)
        }

    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    WeatherAppTheme {
        DetailsScreen(
            modifier = Modifier.background(BackgroundGradient),
            weather = WeatherUiModel(),
            navigationActions = NavigationActions(rememberNavController())
        )
    }
}



