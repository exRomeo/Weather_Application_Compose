package com.trianglz.weatherapp.presentation.viewcontract

sealed class UIAction<T> {
    data class SearchTextChanged<T>(val text: String) : UIAction<T>()
    data class ItemSelected<T>(val item: T, val limit: Int) : UIAction<T>()
}