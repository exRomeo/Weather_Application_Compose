package com.trianglz.weatherapp.presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.domain.usecases.countrysearch.FetchCountriesUseCase
import com.trianglz.weatherapp.domain.usecases.fetchcities.FetchCitiesUseCase
import com.trianglz.weatherapp.domain.usecases.fetchweather.FetchWeatherUseCase
import com.trianglz.weatherapp.presentation.exceptionresolver.ExceptionResolver
import com.trianglz.weatherapp.presentation.mappers.country.toUiModel
import com.trianglz.weatherapp.presentation.mappers.weather.toUiModel
import com.trianglz.weatherapp.presentation.models.result.ResultState
import com.trianglz.weatherapp.presentation.models.result.ResultUiModel
import com.trianglz.weatherapp.presentation.models.searchbar.SearchBarState
import com.trianglz.weatherapp.presentation.models.weathercard.WeatherUiModel
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
    private val exceptionResolver: ExceptionResolver
) : ViewModel() {

    /**
     * [homeUIState] represents the state of the section below the search bar
     * */

    private var _homeUIState: MutableStateFlow<UIState<List<WeatherUiModel>>> =
        MutableStateFlow(UIState.Idle())
    val homeUIState: StateFlow<UIState<List<WeatherUiModel>>>
        get() = _homeUIState


    /**
     * [searchTextState] represents the state of the search query
     * */

    private var _searchTextState: MutableStateFlow<String> = MutableStateFlow("")
    val searchTextState: StateFlow<String>
        get() = _searchTextState


    /**
     * [searchBarState] represents the state of the search bar
     * */

    var searchBarState by mutableStateOf(SearchBarState(placeHolder = "Search For a Country..."))
        private set


    private var _resultState: MutableStateFlow<ResultState<ResultUiModel>> =
        MutableStateFlow(ResultState.Idle())
    val resultState: StateFlow<ResultState<ResultUiModel>>
        get() = _resultState


    /**
     * [uiEvents] is responsible for emitting ui events such as error messages
     * */

    private var _uiEvents: MutableSharedFlow<UIEvent> = MutableSharedFlow()
    val uiEvents: SharedFlow<UIEvent>
        get() = _uiEvents


    /**
     * [updateSearchTextState] responsible for updating [searchTextState]
     * and sets the search bar state to idle with empty result list when the search text is blank
     * */

    private fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
        if (newValue.isBlank()) {
            searchBarState = searchBarState.copy(
                status = ResultState.Idle()
            )
            _resultState.value = ResultState.Idle()
        }
    }


    /**
     * [observeSearchTextState] responsible for observing [searchTextState]
     * and invokes [getCountries] according to each new query while having delays between each search attempt
     * to avoid unnecessary search attempts while the user is still typing
     * */

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


    /**
     * [getCountries] responsible for getting a list of countries that match the query string [countryName]
     * and sets the [searchBarState] according to the success or failure case of the search attempt
     * */

    private suspend fun getCountries(countryName: String) {
        searchBarState =
            searchBarState.copy(
                status = ResultState.Loading()
            )
        val countries = fetchCountries.getCountries(countryName = countryName)

        countries
            .onSuccess { resultList ->
                searchBarState = searchBarState.copy(
                    status = ResultState.Idle()
                )
                _resultState.value = ResultState.Success(resultList.map { it.toUiModel() })

            }.onFailure {
                searchBarState = searchBarState.copy(
                    status = ResultState.NoResult("No Results Found!")
                )
                _resultState.value = ResultState.NoResult(exceptionResolver.resolve(it))

            }
    }


    /**
     * [getWeatherData] responsible for getting the weather data for a number represented by [limit] of cities
     * and in case of success it checks
     * if the list is empty and sets the ui state to failure with a message
     * if the list has cities it calls [getWeather] and hands it the list of cities
     * in case of failure it sets the ui state to failure with a message explaining the issue to the user
     * */

    private fun getWeatherData(country: ResultUiModel, limit: Int) {
        viewModelScope.launch {
            _homeUIState.value = UIState.Loading()
            searchBarState = searchBarState.copy(placeHolder = country.name)
            val cityList = fetchCities.getCities(country.code, limit)
            cityList.onSuccess { cities ->
                if (cities.isEmpty())
                    _homeUIState.value =
                        UIState.Failure("Found no Cities for this country in the database!")
                else
                    getWeather(cities)
            }.onFailure {
                _homeUIState.value = UIState.Failure(exceptionResolver.resolve(it))
            }
        }
    }


    /**
     * [getWeather] responsible for getting and displaying the weather on the ui
     * so it creates a list of "Deferred" types that wraps the calls to [getWeather] and awaits the results
     * then it maps all the results to either weather in case of success or null in case of failure
     * then checks if the list is empty it sets the ui state to failure with message explaining that no weather data is found
     * if the list has data it sets the ui state to success and displays the weather data
     * */

    private suspend fun getWeather(
        cityList: List<CityDataModel>
    ) = coroutineScope {

        val weatherResult =
            cityList.map { city ->
                async {
                    fetchWeather.getWeather(city)
                }
            }.awaitAll()

        val list = weatherResult.mapNotNull { result -> result.getOrNull()?.toUiModel() }

        if (list.isEmpty())
            _homeUIState.value =
                UIState.Failure("Found no weather data for this Country in the database!")
        else
            _homeUIState.value = UIState.Success(list)

    }

    /**
     * [performAction] is a function with one purpose and it is to unify the way that the ui talks to the viewModel
     * using a sealed class with possible actions to be sent from the view to the view model through this function
     * and this function's responsibility is as its name suggests
     * to perform that action
     * */

    fun performAction(action: UIAction<ResultUiModel>) {
        when (action) {

            is UIAction.SearchTextChanged -> updateSearchTextState(action.text)

            is UIAction.ItemSelected -> getWeatherData(action.item, action.limit)
        }
    }


    /**
     * Required to activate the search functionality
     * */
    init {
        observeSearchTextState()
    }
}