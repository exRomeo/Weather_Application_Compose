package com.trianglz.weatherapp.presentation.searchbarstate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.trianglz.weatherapp.domain.models.country.CountryDomainModel

data class SearchBarState(
    val placeHolder: String = "",
    val status: SearchBarStatus = SearchBarStatus.Idle,
    val noResultMessage: String = "",
    val result: List<CountryDomainModel> = emptyList(),
)


@Composable
fun rememberSearchState(
    initialState: SearchBarState = SearchBarState()
) = remember(initialState) {
    initialState
}

@Composable
fun rememberSearchState(
    placeHolder: String = "",
    noResultMessage: String = "",
    status: SearchBarStatus = SearchBarStatus.Idle,
    result: List<CountryDomainModel> = emptyList(),

    ) = remember(
    placeHolder,
    noResultMessage,
    status,
    result,
) {
    SearchBarState(
        placeHolder = placeHolder,
        noResultMessage = noResultMessage,
        status = status,
        result = result,
    )
}
