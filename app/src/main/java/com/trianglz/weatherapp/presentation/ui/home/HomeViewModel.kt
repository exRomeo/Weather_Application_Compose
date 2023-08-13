package com.trianglz.weatherapp.presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.domain.usecases.countrysearch.ICountrySearchUseCase
import com.trianglz.weatherapp.domain.usecases.getweatherdata.IWeatherDataUseCase
import com.trianglz.weatherapp.domain.utils.resource.Resource
import com.trianglz.weatherapp.presentation.searchbarstate.SearchBarStatus
import com.trianglz.weatherapp.presentation.searchbarstate.SearchState
import com.trianglz.weatherapp.presentation.viewcontract.UIAction
import com.trianglz.weatherapp.presentation.viewcontract.UIEvent
import com.trianglz.weatherapp.presentation.viewcontract.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val countrySearch: ICountrySearchUseCase,
    private val weatherData: IWeatherDataUseCase
) : ViewModel() {

    private var _homeUIState: MutableStateFlow<UIState<List<Weather>>> =
        MutableStateFlow(UIState.Idle())
    val homeUIState: StateFlow<UIState<List<Weather>>>
        get() = _homeUIState

    private var _searchTextState: MutableStateFlow<String> = MutableStateFlow("")
    val searchTextState: StateFlow<String>
        get() = _searchTextState


    private var _searchResultState: MutableStateFlow<UIState<List<Country>>> =
        MutableStateFlow(UIState.Idle())
    private val searchResultState: StateFlow<UIState<List<Country>>>
        get() = _searchResultState


    var searchState by mutableStateOf(SearchState())
        private set
    private var currentCountry: String = "Search For a Country..."

    private var _uiEvents: MutableSharedFlow<UIEvent> = MutableSharedFlow()
    val uiEvents: SharedFlow<UIEvent>
        get() = _uiEvents

    private fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
        if (newValue.isBlank())
            _searchResultState.value = UIState.Idle()
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
        _searchResultState.value = UIState.Loading()
        when (val countries = countrySearch.getCountries(countryName = countryName)) {
            is Resource.Success -> _searchResultState.value = UIState.Success(countries.data)
            is Resource.Error -> _searchResultState.value = UIState.Failure(countries.message)
        }
    }

    private fun getWeatherData(country: Country, limit: Int) {
        viewModelScope.launch {
            _homeUIState.value = UIState.Loading()
            currentCountry = country.name.common
            when (val result = weatherData.getWeatherData(country.code, limit)) {
                is Resource.Success -> _homeUIState.value = UIState.Success(result.data)
                is Resource.Error -> {
                    _homeUIState.value = UIState.Failure(result.message)
                    _uiEvents.emit(UIEvent.Message(result.message))
                }
            }
        }
    }


    fun performAction(action: UIAction<Country>) {
        when (action) {

            is UIAction.SearchTextChanged -> updateSearchTextState(action.text)

            is UIAction.ItemSelected -> getWeatherData(action.item, action.limit)
        }
    }


    private fun observeSearchResultState() {
        viewModelScope.launch {
            searchResultState.collect {
                when (it) {

                    is UIState.Idle -> {
                        searchState = searchState.copy(
                            placeHolder = currentCountry,
                            status = SearchBarStatus.Idle,
                            noResultMessage = "",
                            result = emptyList()
                        )
                    }

                    is UIState.Loading -> {
                        searchState =
                            searchState.copy(
                                status = SearchBarStatus.Loading
                            )
                    }

                    is UIState.Success -> {
                        searchState =
                            searchState.copy(
                                status = SearchBarStatus.Idle,
                                result = it.list
                            )
                    }

                    is UIState.Failure -> {
                        searchState =
                            searchState.copy(
                                status = SearchBarStatus.Error,
                                noResultMessage = it.message,
                                result = emptyList()
                            )
                    }
                }
            }
        }
    }

    init {
        observeSearchTextState()
        observeSearchResultState()
    }
}