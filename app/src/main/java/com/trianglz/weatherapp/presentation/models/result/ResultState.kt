package com.trianglz.weatherapp.presentation.models.result


sealed class ResultState<Item> {

    class Idle<Item> : ResultState<Item>()

    class Success<Item>(val list: List<Item>) : ResultState<Item>()

    class NoResult<Item>(val message: String) : ResultState<Item>()

    class Loading<Item> : ResultState<Item>()
}
