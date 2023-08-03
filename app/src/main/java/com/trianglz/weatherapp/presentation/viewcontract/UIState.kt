package com.trianglz.weatherapp.presentation.viewcontract

import com.trianglz.weatherapp.data.models.country.Country

sealed class UIState<T> {
    class Idle<T> : UIState<T>()
    class Loading<T> : UIState<T>()
    data class Success<T>(val list: T) : UIState<T>()
    data class Failure<T>(val message: String) : UIState<T>()
    data class SearchTextChanged<T>(val text: String) : UIState<T>()
    data class SearchResults<T>(val list: List<Country>) : UIState<T>()


}
