package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.R
import com.trianglz.weatherapp.presentation.models.weatherdetails.DailyUiModel
import com.trianglz.weatherapp.presentation.ui.theme.lavender

@Composable
fun DailyForecastCard(
    modifier: Modifier = Modifier,
    dailyList: List<DailyUiModel> = listOf(DailyUiModel()),
    containerColor: Color = Color.White.copy(alpha = 0.05f),
    contentColor: Color = lavender
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .heightIn(max = 800.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Daily Forecast",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(dailyList) { daily ->
                    DailyItemCard(dailyUiModel = daily)
                }
            }
        }

    }
}


@Composable
fun DailyItemCard(modifier: Modifier = Modifier, dailyUiModel: DailyUiModel) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
            contentColor = lavender
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(text = dailyUiModel.time)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.water_droplettes),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = dailyUiModel.pop)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                LottieAnimation(animation = dailyUiModel.icon, Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "${dailyUiModel.high}  |  ${dailyUiModel.low}")
            }
        }
    }
}