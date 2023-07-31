package com.trianglz.weatherapp.core.uistate

import com.trianglz.weatherapp.data.models.weather.Weather

sealed class UIState {
    object Idle : UIState()
    object Loading : UIState()
    class Success(val list: List<Weather>) : UIState()
    class Failure(val message: String) : UIState()
    object NetworkError : UIState()


}
