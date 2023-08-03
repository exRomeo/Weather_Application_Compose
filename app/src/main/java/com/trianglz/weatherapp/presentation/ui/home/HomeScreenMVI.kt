
package com.trianglz.weatherapp.presentation.ui.home
/*

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.getRepository
import com.trianglz.weatherapp.presentation.ui.components.LoadingScreen
import com.trianglz.weatherapp.presentation.ui.components.MessageScreen
import com.trianglz.weatherapp.presentation.ui.components.WeatherCard
import com.trianglz.weatherapp.presentation.ui.components.WeatherSearchBar
import com.trianglz.weatherapp.presentation.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.trianglz.weatherapp.presentation.viewcontract.UIEvent
import com.trianglz.weatherapp.presentation.viewcontract.UIState

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenMVId(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val viewModel: ViewModelMVId = viewModel(
        factory = ViewModelMVIdFactory(
            repository = context.applicationContext.getRepository()
        )
    )

    val snackbarHostState = remember { SnackbarHostState() }
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
        EventProcessor(snackbarHostState = snackbarHostState, uiEvents = viewModel.uiEvents)
        val uiState by viewModel.uiState.collectAsState()



            Box(
                modifier = Modifier
                    .padding(it)
            )  {
                HomeScreenContentMVId(modifier = Modifier.padding(top = 56.dp), uiState = uiState)
                WeatherSearchBar(
                    modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp),
                    placeHolder = "Search for a Country",
                    text = text,
                    onTextChanged = { newValue -> viewModel.updateSearchTextState(newValue) },
                    noResultPlaceHolder = "No Results",
                    onResultsReceived = { results },
                    onResultClicked = { country -> viewModel.getWeatherData(country.code) },
                )
            }

    }
}

@Composable
fun EventProcessor(snackbarHostState: SnackbarHostState, uiEvents: SharedFlow<UIEvent>) {
    LaunchedEffect(Unit) {
        uiEvents.collectLatest {
            when (it) {
                is UIEvent.Message -> snackbarHostState.showSnackbar(it.value)
            }
        }
    }
}

@Composable
fun HomeScreenContentMVId(
    modifier: Modifier = Modifier, uiState: UIState<List<Weather>>
) {
    when (uiState) {

        is UIState.Idle -> MessageScreen(
            modifier = modifier,
            message = "Start searching for a country to display weather details of its top 5 cities"
        )

        is UIState.Loading -> LoadingScreen(modifier = modifier)

        is UIState.Success -> WeatherDataList(modifier = modifier, weatherData = uiState.list)

        is UIState.Failure -> MessageScreen(modifier = modifier, message = uiState.message)
        else -> {}
    }

}


@Composable
fun WeatherDataListMVId(
    modifier: Modifier = Modifier,
    weatherData: List<Weather>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = spacedBy(20.dp)
    ) {
        items(weatherData, key = { it.cityName!! }) {
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
fun HomeScreenPreviewMVId() {
    WeatherAppTheme {
        HomeScreen(modifier = Modifier.background(BackgroundGradient))
    }
}

*/
