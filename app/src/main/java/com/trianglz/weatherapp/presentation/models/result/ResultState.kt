package com.trianglz.weatherapp.presentation.models.result

import androidx.annotation.StringRes


sealed class ResultState<Item> {

    class Idle<Item> : ResultState<Item>()

    class Success<Item>(val list: List<Item>) : ResultState<Item>()

    class NoResult<Item>(@StringRes val message: Int) : ResultState<Item>()

    class Loading<Item> : ResultState<Item>()
}
