package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.presentation.extensions.maxHeightScreenRatio
import com.trianglz.weatherapp.presentation.models.result.ResultItem
import com.trianglz.weatherapp.presentation.models.result.ResultState
import com.trianglz.weatherapp.presentation.models.searchbar.SearchBarState
import com.trianglz.weatherapp.presentation.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme


/**
 * [WeatherSearchWithResults] created as an overload wrapper to its other version to make controlling its state easier
 * */


@Composable
fun <T : ResultItem> WeatherSearchWithResults(
    modifier: Modifier = Modifier,
    searchBarState: SearchBarState = SearchBarState(),
    results: () -> ResultState<T> = { ResultState.Idle() },
    text: () -> String = { "" },
    onTextChanged: (String) -> Unit = {},
    onCloseClicked: () -> Unit = { },
    onItemClicked: (T) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val onCloseActionOne = remember {
        {
            focusManager.clearFocus()
            onCloseClicked()
        }
    }
    val onCloseActionTwo = remember {
        {
            onTextChanged("")
        }
    }

    WeatherSearchWithResults(
        modifier = modifier,
        text = text,
        placeHolder = { searchBarState.placeHolder },
        status = { searchBarState.status },
        onTextChanged = onTextChanged,
        onCloseClicked = if (text().isBlank()) onCloseActionOne else onCloseActionTwo,
    ) {
        Column(
            modifier = Modifier
                .maxHeightScreenRatio(0.5f)
                .animateContentSize()
        ) {
            SearchResultsBox(
                resultState = results,
                onResultClicked = {
                    onItemClicked(it)
                    focusManager.clearFocus()
                    onTextChanged("")
                }
            )
        }
    }
}

/**
 * [WeatherSearchWithResults] is a custom component that has a search bar and accepts a [results] composable
 * that expands\appears when the search bar is in focus which is meant to show the search results
 * @param status represent the current status which is one of 3 [ResultState.Idle], [ResultState.NoResult] and [ResultState.Loading]
 * */

@Composable
fun WeatherSearchWithResults(
    modifier: Modifier = Modifier,
    status: ()-> ResultState<Nothing> = { ResultState.Idle() },
    placeHolder: () -> String = { "" },
    text: () -> String = { "" },
    onCloseClicked: () -> Unit = {},
    onTextChanged: (String) -> Unit = {},
    results: @Composable () -> Unit = {},
) {

    var inFocus by remember { mutableStateOf(false) }

    val elevation = if (inFocus) 30.dp else 0.dp
    val animatedElevation by animateDpAsState(
        targetValue = elevation,
        animationSpec = tween(150, easing = EaseIn),
        label = "animatedElevation"
    )

    val animateBackground by animateColorAsState(
        targetValue = if (inFocus) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.secondaryContainer,
        animationSpec = tween(150, easing = EaseIn),
        label = "Animated Background"
    )
    Card(
        modifier = modifier
            .onFocusChanged { state -> inFocus = state.isFocused }
            .shadow(
                elevation = animatedElevation,
                ambientColor = Color.Black,
                spotColor = Color.Black,
                shape = MaterialTheme.shapes.small
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = animatedElevation),
        colors = CardDefaults.cardColors(containerColor = animateBackground)
    ) {

        TransparentSearchBar(
            status = status,
            placeHolder = placeHolder,
            text = text,
            onTextChanged = onTextChanged,
            onCloseClicked = onCloseClicked
        )

        AnimatedVisibility(visible = inFocus) {
            results()
        }
    }
}


@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_4")
@Composable
fun WeatherSearchWithResultsPreview() {
    WeatherAppTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(BackgroundGradient)
        ) {
            val status by remember { mutableStateOf(ResultState.Idle<Nothing>()) }
            WeatherSearchWithResults(
                status = { status },
                onTextChanged = {},
                onCloseClicked = {}
            )
        }
    }
}