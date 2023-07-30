package com.trianglz.weatherapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.trianglz.weatherapp.data.models.city.City
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.data.repository.IRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: IRepository) : ViewModel() {


    private var _searchTextState: MutableStateFlow<String> = MutableStateFlow("")
    val searchTextState = _searchTextState.asStateFlow()

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
        if (newValue.isEmpty())
            _searchResult.value = emptyList()
    }

    private fun observeSearchTextState() {
        viewModelScope.launch {
            searchTextState.filter { it.isNotBlank() }
                .distinctUntilChanged()
                .debounce(500)
                .collectLatest {
                    Log.i("TAG", "observeSearchTextState: $it")
                    updateWeatherList(clearList = true)
                    getCountries(it, 30)
                }
        }
    }


    private var _searchResult: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    val searchResult: StateFlow<List<Country>>
        get() = _searchResult.asStateFlow()


    fun getCountries(countryName: String, limit: Int) {
        viewModelScope.launch {
            try {
                _searchResult.value =
                    repository.getCountries(countryName = countryName, limit = limit)
            } catch (e: Exception) {
                Log.i("TAG", "getCountries Exception: ${e.message}")
            }
        }
    }

    private var _cities: MutableStateFlow<List<City>> = MutableStateFlow(emptyList())
    private val cities = _cities.asStateFlow()

    fun getCities(countryCode: String, limit: Int = 5) {
        viewModelScope.launch {
            try {
                _cities.value = repository.getCities(
                    countryCode = countryCode,
                    limit = limit
                )

            } catch (e: Exception) {
                Log.i("TAG", "getCities Exception: ${e.message}")
            }
        }
    }

    private fun observeCities() {
        viewModelScope.launch {
            cities.collectLatest {
                it.forEach { city ->
                    getWeather(city)

                }
            }
        }
    }

    private var weatherDataList: MutableList<Weather> = mutableListOf()
    private var _weatherData: MutableStateFlow<List<Weather>> = MutableStateFlow(emptyList())
    val weatherData = _weatherData.asStateFlow()


    private fun updateWeatherList(weather: Weather? = null, clearList: Boolean = false) {
        if (clearList)
            weatherDataList.clear()
        if (weather != null) {
            weatherDataList = ArrayList(weatherDataList)
            weatherDataList.add(weather)
        }
        _weatherData.value = weatherDataList
    }

    private suspend fun getWeather(city: City) {
        try {
            val weather = repository.getWeather(city.latitude, city.longitude)
            weather.apply {
                cityName = city.name
                countryCode = city.country
            }
            updateWeatherList(weather = weather)
        } catch (e: Exception) {
            Log.i("TAG", "getWeather Exception:$city ${e.message}")
        }
    }

    init {
        observeSearchTextState()
        observeCities()
    }
}

class HomeViewModelFactory(private val repository: IRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            HomeViewModel(repository) as T
        else throw Exception("Couldn't find ViewModel Class")
    }
}