package com.trianglz.weatherapp.presentation.viewcontract

sealed class UIEvent {
    data class Message(val message: String) : UIEvent()
}
