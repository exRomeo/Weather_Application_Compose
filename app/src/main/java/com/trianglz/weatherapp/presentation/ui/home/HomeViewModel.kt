package com.trianglz.weatherapp.presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trianglz.weatherapp.domain.models.country.CountryDomainModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.domain.usecases.countrysearch.FetchCountriesUseCase
import com.trianglz.weatherapp.domain.usecases.fetchcities.FetchCitiesUseCase
import com.trianglz.weatherapp.domain.usecases.fetchweather.FetchWeatherUseCase
import com.trianglz.weatherapp.domain.utils.UtilityManager
import com.trianglz.weatherapp.presentation.searchbarstate.SearchBarState
import com.trianglz.weatherapp.presentation.searchbarstate.SearchBarStatus
import com.trianglz.weatherapp.presentation.viewcontract.UIAction
import com.trianglz.weatherapp.presentation.viewcontract.UIEvent
import com.trianglz.weatherapp.presentation.viewcontract.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCountries: FetchCountriesUseCase,
    private val fetchCities: FetchCitiesUseCase,
    private val fetchWeather: FetchWeatherUseCase,
    private val utilityManager: UtilityManager
) : ViewModel() {

    private var _homeUIState: MutableStateFlow<UIState<List<WeatherDomainModel>>> =
        MutableStateFlow(UIState.Idle())
    val homeUIState: StateFlow<UIState<List<WeatherDomainModel>>>
        get() = _homeUIState

    private var _searchTextState: MutableStateFlow<String> = MutableStateFlow("")
    val searchTextState: StateFlow<String>
        get() = _searchTextState


    var searchBarState by mutableStateOf(SearchBarState(placeHolder = "Search For a Country..."))
        private set

    private var _uiEvents: MutableSharedFlow<UIEvent> = MutableSharedFlow()
    val uiEvents: SharedFlow<UIEvent>
        get() = _uiEvents

    private fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
        if (newValue.isBlank())
            searchBarState = searchBarState.copy(
                status = SearchBarStatus.Idle,
                noResultMessage = "",
                result = emptyList()
            )
    }

    private fun observeSearchTextState() {
        viewModelScope.launch {
            searchTextState
                .collectLatest {
                    delay(250)
                    if (it.isNotBlank())
                        getCountries(it)
                }
        }
    }

    private suspend fun getCountries(countryName: String) {
        searchBarState =
            searchBarState.copy(
                status = SearchBarStatus.Loading
            )
        val countries = fetchCountries.getCountries(countryName = countryName)
        searchBarState =
            if (countries.isSuccess) {
                searchBarState.copy(
                    status = SearchBarStatus.Idle,
                    result = countries.getOrDefault(emptyList())
                )
            } else {
                searchBarState.copy(
                    status = SearchBarStatus.Error,
                    noResultMessage = countries.exceptionOrNull()?.message
                        ?: "Something went wrong!, please try again later",
                    result = emptyList()
                )
            }
    }

    private fun getWeatherData(country: CountryDomainModel, limit: Int) {
        viewModelScope.launch {
            _homeUIState.value = UIState.Loading()
            searchBarState = searchBarState.copy(placeHolder = country.name)
            val result = getWeatherData(country.code, limit)
            if (result.isSuccess) {
                _homeUIState.value = UIState.Success(result.getOrDefault(emptyList()))
            } else {
                _homeUIState.value = UIState.Failure(
                    result.exceptionOrNull()?.message
                        ?: "Something went wrong!, please try again later"
                )
                _uiEvents.emit(
                    UIEvent.Message(
                        result.exceptionOrNull()?.message
                            ?: "Something went wrong!, please try again later"
                    )
                )
            }
        }
    }

    private suspend fun getWeatherData(
        countryCode: String,
        limit: Int
    ): Result<List<WeatherDomainModel>> = coroutineScope {
        try {
            val list = fetchCities.getCities(countryCode, limit).map {
                async {
                    fetchWeather.getWeather(it)
                }
            }.awaitAll().map { it.getOrNull() }.mapNotNull { it }.toList()

            if (list.isEmpty())
                Result.failure(Exception("This Country Has No Weather Data"))
            else
                Result.success(list)

        } catch (exception: Exception) {
            Result.failure(Exception(utilityManager.handleException(exception)))
        }
    }


    fun performAction(action: UIAction<CountryDomainModel>) {
        when (action) {

            is UIAction.SearchTextChanged -> updateSearchTextState(action.text)

            is UIAction.ItemSelected -> getWeatherData(action.item, action.limit)
        }
    }


    init {
        observeSearchTextState()
    }
}