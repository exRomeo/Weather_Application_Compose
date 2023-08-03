package com.trianglz.weatherapp.presentation.searchbarstate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.trianglz.weatherapp.data.models.country.Country

data class SearchBarState(
    var text: String = "",
    var onTextChanged: (String) -> Unit = {},
    var placeHolder: String = "",
    var noResultMessage: String = "",
    var result: List<Country> = emptyList(),
    var onItemClicked: (Country) -> Unit = {}
)


@Composable
fun rememberSearchBarState(
    initialState: SearchBarState = SearchBarState()
) = remember(initialState) {
    initialState
}

@Composable
fun rememberSearchBarState(
    text: String = "",
    onTextChanged: (String) -> Unit = {},
    placeHolder: String = "",
    noResultMessage: String = "",
    result: List<Country> = emptyList(),
    onItemClicked: (Country) -> Unit = {}
) = remember(
    text,
    onTextChanged,
    placeHolder,
    noResultMessage,
    result,
    onItemClicked
) {
    SearchBarState(
        text =  text,
        onTextChanged = onTextChanged,
        placeHolder = placeHolder,
        noResultMessage = noResultMessage,
        result = result,
        onItemClicked = onItemClicked
    )
}
