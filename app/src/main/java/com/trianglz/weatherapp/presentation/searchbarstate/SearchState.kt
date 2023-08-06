package com.trianglz.weatherapp.presentation.searchbarstate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.presentation.ui.components.SearchBarStatus

data class SearchState(
    var noResultMessage: String = "",
    var status: SearchBarStatus = SearchBarStatus.Idle,
    var result: List<Country> = emptyList(),
)


@Composable
fun rememberSearchState(
    initialState: SearchState = SearchState()
) = remember(initialState) {
    initialState
}

@Composable
fun rememberSearchState(
    noResultMessage: String = "",
    status: SearchBarStatus = SearchBarStatus.Idle,
    result: List<Country> = emptyList(),

    ) = remember(
    noResultMessage,
    status,
    result,
) {
    SearchState(
        noResultMessage = noResultMessage,
        status = status,
        result = result,
    )
}
