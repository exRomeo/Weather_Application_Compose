package com.trianglz.weatherapp.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trianglz.weatherapp.BuildConfig
import com.trianglz.weatherapp.core.apiservice.apininja.ApiNinjaClient
import com.trianglz.weatherapp.core.apiservice.restcountries.RestCountriesClient
import com.trianglz.weatherapp.core.uistate.UIState
import com.trianglz.weatherapp.core.utils.UtilityManager
import com.trianglz.weatherapp.core.utils.connection.ConnectionUtility
import com.trianglz.weatherapp.core.utils.exceptionhandler.ExceptionHandler
import com.trianglz.weatherapp.data.datasource.RemoteDataSource
import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.data.repository.Repository
import com.trianglz.weatherapp.ui.components.LoadingScreen
import com.trianglz.weatherapp.ui.components.MessageScreen
import com.trianglz.weatherapp.ui.components.WeatherCard
import com.trianglz.weatherapp.ui.components.WeatherSearchBar
import com.trianglz.weatherapp.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.ui.theme.WeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            repository = Repository(
                RemoteDataSource(
                    ninjaApi = ApiNinjaClient().ninjaApi,
                    apiKey = BuildConfig.APIKEY,
                    restCountriesAPI = RestCountriesClient().restCountriesAPI
                ),
            ),
            utilityManager = UtilityManager(
                connectionUtility = ConnectionUtility(LocalContext.current.applicationContext),
                exceptionHandler = ExceptionHandler()
            )
        )
    )

    val state by viewModel.homeUIState.collectAsState()

    Log.i("TAG", "UISTATE: ${state.javaClass}")
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
        val text by viewModel.searchTextState.collectAsState()
        val results by viewModel.searchResult.collectAsState()
        val uiState by viewModel.homeUIState.collectAsState()
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            Box {
                HomeScreenContent(modifier = Modifier.padding(top = 48.dp), uiState = uiState)
                WeatherSearchBar(
                    modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp),
                    placeHolder = "Search for a Country",
                    text = text,
                    onTextChanged = { newValue -> viewModel.updateSearchTextState(newValue) },
                    noResultPlaceHolder = "No Results",
                    onResultsReceived = { results },
                    onResultClicked = { country -> viewModel.getCities(country.code) },
                )
            }
        }
    }
}

@Composable
fun HomeScreenContent(modifier: Modifier = Modifier, uiState: UIState) {
    when (uiState) {
        is UIState.Idle -> MessageScreen(
            modifier = modifier,
            message = "Start searching for a country to display weather details of its top 5 cities"
        )

        is UIState.NetworkError -> MessageScreen(
            modifier = modifier,
            message = "Please, check your internet connection"
        )

        is UIState.Failure -> MessageScreen(modifier = modifier, message = uiState.message)
        is UIState.Loading -> LoadingScreen(modifier = modifier)
        is UIState.Success -> WeatherDataList(modifier = modifier, weatherData = uiState.list)
    }
}


@Composable
fun WeatherDataList(
    modifier: Modifier = Modifier,
    weatherData: List<Weather>
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
                location = "${it.cityName}, ${it.countryCode}",
                description = it.getWeatherDescription(),
                icon = it.getWeatherIcon()
            )
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
