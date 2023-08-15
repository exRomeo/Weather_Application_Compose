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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.domain.models.country.CountryDomainModel
import com.trianglz.weatherapp.presentation.extensions.maxHeightScreenRatio
import com.trianglz.weatherapp.presentation.searchbarstate.SearchBarState
import com.trianglz.weatherapp.presentation.searchbarstate.SearchBarStatus
import com.trianglz.weatherapp.presentation.searchbarstate.rememberSearchState
import com.trianglz.weatherapp.presentation.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.trianglz.weatherapp.presentation.ui.theme.darkPurple
import com.trianglz.weatherapp.presentation.ui.theme.lavender


@Composable
fun WeatherSearchBar(
    modifier: Modifier = Modifier,
    searchBarState: SearchBarState = rememberSearchState(),
    text: String = "",
    onTextChanged: (String) -> Unit = {},
    onCloseClicked: () -> Unit = { },
    onItemClicked: (CountryDomainModel) -> Unit = {}
) {
    WeatherSearchBar(
        modifier = modifier,
        text = text,
        placeHolder = searchBarState.placeHolder,
        noResultPlaceHolder = searchBarState.noResultMessage,
        status = searchBarState.status,
        results = searchBarState.result,
        onTextChanged = onTextChanged,
        onCloseClicked = onCloseClicked,
        onResultClicked = onItemClicked
    )
}

@Composable
fun WeatherSearchBar(
    modifier: Modifier = Modifier,
    status: SearchBarStatus = SearchBarStatus.Idle,
    placeHolder: String = "",
    text: String = "",
    onCloseClicked: () -> Unit = {},
    noResultPlaceHolder: String = "",
    results: List<CountryDomainModel> = listOf(),
    onResultClicked: (CountryDomainModel) -> Unit = {},
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
        targetValue = if (inFocus) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.secondaryContainer,
        animationSpec = tween(150, easing = EaseIn),
        label = "Animated Background"
    )
    Card(
        modifier = modifier.shadow(
            elevation = animatedElevation,
            ambientColor = Color.Black,
            spotColor = Color.Black,
            shape = MaterialTheme.shapes.small
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = animatedElevation),
        colors = CardDefaults.cardColors(containerColor = animateBackground)
    ) {
        TransparentSearchBar(
            modifier = Modifier
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
                            else {
                                focusManager.clearFocus()
                                onCloseClicked()
                            }

                        },
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            },
            leadingIcon = {
                when (status) {
                    is SearchBarStatus.Idle -> Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = animatedAlpha)
                    )

                    is SearchBarStatus.Loading -> CircularProgressIndicator(
                        modifier = Modifier.scale(scale = 0.6f),
                        strokeWidth = 5.dp,
                        strokeCap = StrokeCap.Round,
                        trackColor = darkPurple
                    )

                    is SearchBarStatus.Error -> Icon(
                        imageVector = Icons.Rounded.Warning,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onErrorContainer
                    )

                }
            },
            text = text,
            textStyle = MaterialTheme
                .typography
                .bodyLarge
                .copy(
                    color = if (status is SearchBarStatus.Error)
                        MaterialTheme.colorScheme.onErrorContainer
                    else
                        MaterialTheme.colorScheme.onPrimaryContainer
                ),
            placeHolder = {
                Text(
                    text = placeHolder,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            },
            onTextChanged = onTextChanged
        )
        AnimatedVisibility(visible = inFocus) {
            Column(
                modifier = Modifier
                    .maxHeightScreenRatio(0.5f)
                    .animateContentSize()
            ) {
                SearchResultsBox(countries = results,
                    noResultPlaceHolder = noResultPlaceHolder,
                    onResultClicked = {
                        onResultClicked(it)
                        focusManager.clearFocus()
                        onTextChanged("")
                    })
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentSearchBar(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: (@Composable () -> Unit)? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    singleLine: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    onTextChanged: (String) -> Unit,
) {
    BasicTextField(modifier = modifier.fillMaxWidth(),
        value = text,
        singleLine = singleLine,
        onValueChange = onTextChanged,
        cursorBrush = SolidColor(lavender),
        textStyle = textStyle,
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = text,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(vertical = 7.dp, horizontal = 8.dp),
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                placeholder = placeHolder,
                shape = shape,
            ) { }
        })
}

@Composable
fun SearchResultsBox(
    modifier: Modifier = Modifier,
    countries: List<CountryDomainModel>,
    noResultPlaceHolder: String,
    onResultClicked: (CountryDomainModel) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(countries, key = { it.code }) { country ->
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
                    text = country.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (country != countries.last()) Divider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (countries.isEmpty() && noResultPlaceHolder.isNotBlank()) item {
            Row(
                Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = noResultPlaceHolder,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
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
            var status by remember { mutableStateOf(SearchBarStatus.Idle) }
            WeatherSearchBar(
                modifier = Modifier.padding(8.dp),
                placeHolder = "Search For A Country...",
                status = status,
                onCloseClicked = { status = SearchBarStatus.Idle },

                onTextChanged = {},
                text = "",
                noResultPlaceHolder = "Emptee",
                onResultClicked = {},
                results = emptyList()
            )
        }
    }
}
