package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.presentation.models.result.ResultItem
import com.trianglz.weatherapp.presentation.models.result.ResultState

/**
 * [SearchResultsBox] is just a transparent [Column] that shows the received country list or an error message
 * @param resultState represents the state of the received list
 * @param onResultClicked is a callback that is called when a result is clicked
 *
 * */


@Composable
fun <T : ResultItem> SearchResultsBox(
    modifier: Modifier = Modifier,
    resultState:()-> ResultState<T>,
    onResultClicked: (T) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {

        when (val resultState = resultState()) {
            is ResultState.Success -> {
                val items = resultState.list
                items(items, key = { it.hashCode() }) { item ->
                    Row(
                        Modifier
                            .height(48.dp)
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.small)
                            .clickable { onResultClicked(item) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 48.dp)
                                .fillMaxWidth(),
                            text = item.name,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    if (item != items.last()) Divider(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            is ResultState.NoResult -> {
                item {
                    Row(
                        Modifier
                            .height(48.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 48.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = resultState.message,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            else -> {}
        }
    }
}