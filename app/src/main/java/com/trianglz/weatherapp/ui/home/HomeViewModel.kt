package com.trianglz.weatherapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.trianglz.weatherapp.core.uistate.UIState
import com.trianglz.weatherapp.core.utils.IUtilityManager
import com.trianglz.weatherapp.data.models.city.City
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.data.repository.IRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: IRepository,
    private val utilityManager: IUtilityManager
) : ViewModel() {

    private var _homeUIState: MutableStateFlow<UIState> = MutableStateFlow(UIState.Idle)
    val homeUIState = _homeUIState.asStateFlow()

    private var _searchTextState: MutableStateFlow<String> = MutableStateFlow("")
    val searchTextState = _searchTextState.asStateFlow()

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
                    updateWeatherList(clearList = true)
                    getCountries(it)
                }
        }
    }


    private var _searchResult: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    val searchResult: StateFlow<List<Country>>
        get() = _searchResult.asStateFlow()


    private fun getCountries(countryName: String) {
        if (utilityManager.isInternetAvailable())
            viewModelScope.launch {
                try {
                    _searchResult.value =
                        repository.getCountries(countryName = countryName)
                } catch (e: Throwable) {
                    _homeUIState.value = UIState.Failure(utilityManager.handleException(e))
                    _searchResult.value = emptyList()
                }
            }
        else _homeUIState.value = UIState.NetworkError
    }

    private var _cities: MutableStateFlow<List<City>> = MutableStateFlow(emptyList())
    private val cities = _cities.asStateFlow()

    fun getCities(countryCode: String, limit: Int = 5) {
        if (utilityManager.isInternetAvailable())
            viewModelScope.launch {
                _homeUIState.value = UIState.Loading
                try {
                    _cities.value = repository.getCities(
                        countryCode = countryCode,
                        limit = limit
                    )
                } catch (e: Throwable) {
                    _homeUIState.value = UIState.Failure(utilityManager.handleException(e))
                }
            }
        else _homeUIState.value = UIState.NetworkError

    }

    private var weatherDataList: MutableList<Weather> = mutableListOf()

    private fun observeCities() {
        viewModelScope.launch {
            cities.filter { it.isNotEmpty() }.collectLatest {

                _homeUIState.value = UIState.Loading
                it.forEach { city ->
                    getWeather(city)
                }
                _homeUIState.value =
                    UIState.Success(weatherDataList)

            }
        }
    }


    private fun updateWeatherList(weather: Weather? = null, clearList: Boolean = false) {
        if (clearList)
            weatherDataList = mutableListOf()
        if (weather != null) {
            weatherDataList = ArrayList(weatherDataList)
            weatherDataList.add(weather)
        }

    }

    private suspend fun getWeather(city: City) {
        if (utilityManager.isInternetAvailable())
            try {
                val weather = repository.getWeather(city.latitude, city.longitude)
                weather.apply {
                    cityName = city.name
                    countryCode = city.country
                }
                updateWeatherList(weather = weather)

            } catch (e: Throwable) {
                _homeUIState.value = UIState.Failure(utilityManager.handleException(e))
            }
        else _homeUIState.value = UIState.NetworkError

    }

    init {
        observeSearchTextState()
        observeCities()
        if (!utilityManager.isInternetAvailable())
            _homeUIState.value = UIState.NetworkError
    }
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val repository: IRepository, private val utilityManager: IUtilityManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            HomeViewModel(repository, utilityManager) as T
        else throw Exception("Couldn't find ViewModel Class")
    }
}