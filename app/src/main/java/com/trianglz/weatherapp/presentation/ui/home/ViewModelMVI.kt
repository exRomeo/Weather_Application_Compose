
package com.trianglz.weatherapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope

import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.domain.repository.IRepository
import com.trianglz.weatherapp.domain.utils.resource.Resource
import com.trianglz.weatherapp.presentation.viewcontract.UIAction
import com.trianglz.weatherapp.presentation.viewcontract.UIEvent
import com.trianglz.weatherapp.presentation.viewcontract.UIState

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class ViewModelMVI(
    private val repository: IRepository
) : BaseViewModelMVI<UIState<List<Weather>>, UIEvent, UIAction>() {

    override val initialUIState: UIState<List<Weather>> = UIState.Idle()

    private var _searchTextState: MutableStateFlow<String> = MutableStateFlow("")

    override fun processAction(action: UIAction) {
        when (action) {
            is UIAction.Search -> updateSearchTextState(action.query)
            is UIAction.GetWeather -> getWeatherData(action.countryCode, action.limit)
        }
    }


    private fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
        updateUIState(UIState.SearchTextChanged(newValue))
        if (newValue.isBlank())
            updateUIState(UIState.SearchResults(emptyList()))
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchTextState() {
        viewModelScope.launch {
            _searchTextState.filter { it.isNotBlank() }
                .distinctUntilChanged()
                .debounce(350)
                .collectLatest {
                    getCountries(it)
                }
        }
    }

    private fun getCountries(countryName: String) {
        viewModelScope.launch {
            when (val countries = repository.getCountries(countryName = countryName)) {
                is Resource.Success -> updateUIState(UIState.SearchResults(countries.data))
                is Resource.Error -> {
                    sendEvent(UIEvent.Message(countries.message))
                    updateUIState(UIState.SearchResults(emptyList()))
                }
            }
        }
    }

    private fun getWeatherData(countryCode: String, limit: Int = 5) {
        viewModelScope.launch {
            updateUIState(UIState.Loading())
            when (val result = repository.getWeatherData(countryCode, limit)) {
                is Resource.Success -> updateUIState(UIState.Success(result.data))
                is Resource.Error -> sendEvent(UIEvent.Message(result.message))
            }
        }
    }

    init {
        observeSearchTextState()
    }
}

@Suppress("UNCHECKED_CAST")
class ViewModelMVIdFactory(
    private val repository: IRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            ViewModelMVI(repository) as T
        else throw Exception("Couldn't find ViewModel Class")
    }
}
