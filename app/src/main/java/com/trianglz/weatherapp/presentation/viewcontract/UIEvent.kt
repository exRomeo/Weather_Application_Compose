package com.trianglz.weatherapp.presentation.viewcontract

sealed class UIEvent {
    data class Message(val value: String) : UIEvent()
}
