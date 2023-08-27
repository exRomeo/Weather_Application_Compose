package com.trianglz.weatherapp.presentation.viewcontract

sealed class UIState<T> {
    class Idle<T> : UIState<T>()
    class Loading<T> : UIState<T>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Failure<T>(val message: String) : UIState<T>()
}
