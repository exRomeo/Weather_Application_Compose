package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.presentation.searchbarstate.SearchBarState
import com.trianglz.weatherapp.presentation.searchbarstate.rememberSearchBarState
import com.trianglz.weatherapp.presentation.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.trianglz.weatherapp.presentation.ui.theme.lavender


@Composable
fun WeatherSearchBar(
    modifier: Modifier = Modifier,
    searchBarState: SearchBarState = rememberSearchBarState()
) {
    WeatherSearchBar(
        modifier = modifier,
        text = searchBarState.text,
        placeHolder = searchBarState.placeHolder,
        onTextChanged = searchBarState.onTextChanged,
        onResultsReceived = { searchBarState.result },
        onResultClicked = searchBarState.onItemClicked,
        noResultPlaceHolder = searchBarState.noResultMessage
    )
}

@Composable
fun WeatherSearchBar(
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    text: String = "",
    noResultPlaceHolder: String = "",
    onResultsReceived: () -> List<Country> = { listOf() },
    onResultClicked: (Country) -> Unit = {},
    onTextChanged: (String) -> Unit = {}
) {

    var inFocus by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val elevation = if (inFocus) 30.dp else 0.dp
    val animatedElevation by animateDpAsState(
        targetValue = elevation,
        animationSpec = tween(150, easing = EaseIn),
        label = "animatedElevation"
    )
    val alphaValue = if (inFocus) 1f else 0.5f
    val animatedAlpha by animateFloatAsState(
        targetValue = alphaValue,
        animationSpec = tween(150, easing = EaseIn),
        label = "animatedAlpha"
    )
    val animateBackground by animateColorAsState(
        targetValue = if (inFocus)
            MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.secondaryContainer,
        animationSpec = tween(150, easing = EaseIn),
        label = "Animated Background"
    )
    Card(
        modifier = modifier
            .shadow(
                elevation = animatedElevation,
                ambientColor = Color.Black,
                spotColor = Color.Black,
                shape = MaterialTheme.shapes.small
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = animatedElevation),
        colors = CardDefaults.cardColors(containerColor = animateBackground)
    ) {
        PurpleSearchBar(
            modifier =
            Modifier
                .clip(MaterialTheme.shapes.small)
                .onFocusChanged { state -> inFocus = state.isFocused },
            trailingIcon = {
                AnimatedVisibility(
                    visible = inFocus,
                    enter = fadeIn(animationSpec = tween(150, easing = EaseIn)),
                    exit = fadeOut(animationSpec = tween(150, easing = EaseOut))
                ) {
                    Icon(
                        modifier = Modifier.clickable {

                            if (text.isNotEmpty())
                                onTextChanged("")
                            else
                                focusManager.clearFocus()
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = animatedAlpha)
                )
            },
            text = text,
            placeHolder = {
                Text(
                    text = placeHolder,
                    style = MaterialTheme
                        .typography
                        .bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                )
            },
            shape = MaterialTheme.shapes.small,
            onTextChanged = onTextChanged
        )
        AnimatedVisibility(visible = inFocus) {
            Column(
                modifier = Modifier
                    .heightIn(max = 400.dp)
                    .animateContentSize()
            ) {
                SearchResultsBox(
                    countries = onResultsReceived(),
                    noResultPlaceHolder = noResultPlaceHolder,
                    onResultClicked = {
                        onResultClicked(it)
                        focusManager.clearFocus()
                        onTextChanged("")
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurpleSearchBar(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: (@Composable () -> Unit)? = null,
    fontSize: TextUnit = MaterialTheme.typography.bodyLarge.fontSize,
    shape: CornerBasedShape,
    singleLine: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    onTextChanged: (String) -> Unit,
) {
    BasicTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        singleLine = singleLine,
        onValueChange = onTextChanged,
        cursorBrush = SolidColor(lavender),
        textStyle = TextStyle(color = lavender, fontSize),
        decorationBox =
        { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = text,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(vertical = 12.dp, horizontal = 8.dp),
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                placeholder = placeHolder,
                shape = shape
            ) { }
        }
    )

}

@Composable
fun SearchResultsBox(
    modifier: Modifier = Modifier,
    countries: List<Country>,
    noResultPlaceHolder: String,
    onResultClicked: (Country) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(countries) { country ->
            Row(
                Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
                    .clickable { onResultClicked(country) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 48.dp)
                        .fillMaxWidth(),
                    text = country.name.official
                )
            }
            if (country != countries.last())
                Divider(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
        }

        if (countries.isEmpty() && noResultPlaceHolder.isNotEmpty())
            item {
                Row(
                    Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = noResultPlaceHolder)
                }
            }

    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_4")
@Composable
fun WeatherSearchBarPreview() {
    WeatherAppTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(BackgroundGradient)
        ) {
            WeatherSearchBar(
                modifier = Modifier.padding(8.dp),
                placeHolder = "Search For A Country...",
                onTextChanged = {},
                text = "",
                noResultPlaceHolder = "",
                onResultClicked = {}, onResultsReceived = { emptyList() }
            )
        }
    }
}
