package com.trianglz.weatherapp.presentation.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trianglz.weatherapp.domain.usecases.fetchweatherdetails.FetchWeatherDetailsUseCase
import com.trianglz.weatherapp.presentation.mappers.errors.toUi
import com.trianglz.weatherapp.presentation.mappers.weather.toDomainModel
import com.trianglz.weatherapp.presentation.mappers.weatherdetails.toUiModel
import com.trianglz.weatherapp.presentation.models.weathercard.WeatherUiModel
import com.trianglz.weatherapp.presentation.models.weatherdetails.WeatherDetailsUiModel
import com.trianglz.weatherapp.presentation.viewcontract.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel
@Inject constructor(
    private val weatherDetailsUseCase: FetchWeatherDetailsUseCase,
) : ViewModel() {

    private var _uiState: MutableStateFlow<UIState<WeatherDetailsUiModel>> =
        MutableStateFlow(UIState.Loading())

    val uiState: StateFlow<UIState<WeatherDetailsUiModel>>
        get() = _uiState

    private lateinit var currentWeather: WeatherUiModel

    var refreshing by mutableStateOf(false)
    private set
    fun getWeatherDetails(weather: WeatherUiModel) {
        currentWeather = weather
        viewModelScope.launch {
            weatherDetailsUseCase.getWeatherDetails(
                weatherDomainModel = weather.toDomainModel(),
                lang = "en",
                unit = "metric",
                exclude = "minutely"
            ).onRight {
                _uiState.value = UIState.Success(it.toUiModel())
            }.onLeft {
                _uiState.value = UIState.Failure(it.toUi())
            }
            refreshing = false
        }
    }

    fun refresh() {
        _uiState.value = UIState.Loading()
        refreshing = true
        getWeatherDetails(currentWeather)
    }
}