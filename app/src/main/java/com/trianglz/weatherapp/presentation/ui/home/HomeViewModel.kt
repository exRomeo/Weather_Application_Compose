package com.trianglz.weatherapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.domain.repository.IRepository
import com.trianglz.weatherapp.domain.utils.resource.Resource
import com.trianglz.weatherapp.presentation.viewcontract.UIState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
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


    private var _searchResult: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    val searchResult: StateFlow<List<Country>>
        get() = _searchResult


    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
        if (newValue.isBlank())
            _searchResult.value = emptyList()
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchTextState() {
        viewModelScope.launch {
            searchTextState.filter { it.isNotBlank() }
                .distinctUntilChanged()
                .debounce(500)
                .collectLatest {
                    getCountries(it)
                }
        }
    }

    private fun getCountries(countryName: String) {
        viewModelScope.launch {
            when (val countries = repository.getCountries(countryName = countryName)) {
                is Resource.Success -> _searchResult.value = countries.data
                is Resource.Error -> {
                    _homeUIState.value = UIState.Failure(countries.message)
                    _searchResult.value = emptyList()
                }
            }
        }
    }

    fun getWeatherData(countryCode: String, limit: Int = 5) {
        viewModelScope.launch {
            _homeUIState.value = UIState.Loading()
            when (val result = repository.getWeatherData(countryCode, limit)) {
                is Resource.Success -> _homeUIState.value = UIState.Success(result.data)
                is Resource.Error -> _homeUIState.value = UIState.Failure(result.message)
            }
        }
    }

    init {
        observeSearchTextState()
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