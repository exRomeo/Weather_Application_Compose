package com.trianglz.weatherapp.presentation.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.domain.repository.IRepository
import com.trianglz.weatherapp.domain.utils.resource.Resource
import com.trianglz.weatherapp.presentation.searchbarstate.SearchState
import com.trianglz.weatherapp.presentation.ui.components.SearchBarStatus
import com.trianglz.weatherapp.presentation.viewcontract.UIAction
import com.trianglz.weatherapp.presentation.viewcontract.UIEvent
import com.trianglz.weatherapp.presentation.viewcontract.UIState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: IRepository
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


    val searchState = mutableStateOf(SearchState())

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
            searchTextState.debounce(250)
                .collect {
                    if (it.isNotBlank())
                        getCountries(it)
                }
        }
    }

    private suspend fun getCountries(countryName: String) {
        _searchResultState.value = UIState.Loading()
        when (val countries = repository.getCountries(countryName = countryName)) {
            is Resource.Success -> _searchResultState.value = UIState.Success(countries.data)
            is Resource.Error -> _searchResultState.value = UIState.Failure(countries.message)
        }
    }

    private fun getWeatherData(countryCode: String, limit: Int) {
        viewModelScope.launch {
            _homeUIState.value = UIState.Loading()
            when (val result = repository.getWeatherData(countryCode, limit)) {
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

            is UIAction.ItemSelected -> getWeatherData(action.item.code, action.limit)
        }
    }


    private fun observeSearchResultState() {
        viewModelScope.launch {
            searchResultState.collect {
                when (it) {

                    is UIState.Idle -> {
                        searchState.value = SearchState()
                    }

                    is UIState.Loading -> {
                        searchState.value =
                            searchState.value.copy(status = SearchBarStatus.Loading)
                    }

                    is UIState.Success -> {
                        searchState.value = SearchState(result = it.list)
                    }

                    is UIState.Failure -> {
                        searchState.value =
                            SearchState(
                                status = SearchBarStatus.Error,
                                noResultMessage = it.message
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

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val repository: IRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            HomeViewModel(repository) as T
        else throw Exception("Couldn't find ViewModel Class")
    }
}