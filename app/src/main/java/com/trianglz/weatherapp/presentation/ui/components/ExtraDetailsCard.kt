package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.presentation.models.weatherdetails.ExtraDetailUiModel
import com.trianglz.weatherapp.presentation.ui.theme.lavender

@Composable
fun ExtraDetailsCard(
    modifier: Modifier = Modifier,
    list: List<ExtraDetailUiModel>,
    containerColor: Color = Color.White.copy(alpha = 0.05f),
    contentColor: Color = lavender
) {
    Column(
        modifier = modifier
            .sizeIn(maxHeight = 400.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = spacedBy(8.dp),
            verticalArrangement = spacedBy(8.dp)
        ) {
            items(list) { detail ->
                ExtraDetailItemCard(
                    extraDetailUiModel = detail,
                    containerColor = containerColor,
                    contentColor = contentColor
                )
            }
        }
    }
}

@Composable
private fun ExtraDetailItemCard(
    modifier: Modifier = Modifier,
    extraDetailUiModel: ExtraDetailUiModel,
    containerColor: Color,
    contentColor: Color
) {
    Card(
        modifier = modifier.size(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = extraDetailUiModel.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Image(
                modifier = Modifier.size(32.dp).padding(2.dp),
                painter = painterResource(id = extraDetailUiModel.icon),
                contentDescription = null
            )
            Text(
                text = extraDetailUiModel.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}