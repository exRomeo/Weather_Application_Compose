package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.trianglz.weatherapp.presentation.models.weatherdetails.HourlyUiModel
import com.trianglz.weatherapp.presentation.ui.theme.lavender

@Composable
fun HourlyForecastCard(
    modifier: Modifier = Modifier,
    hourlyList: List<HourlyUiModel>,
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
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Hourly Forecast",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            LazyRow(
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = spacedBy(8.dp)
            ) {
                items(hourlyList) { hourly ->
                    HourlyItemCard(
                        hourlyUiModel = hourly,
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                        contentColor = lavender
                    )
                }
            }

        }
    }
}

@Composable
private fun HourlyItemCard(
    modifier: Modifier = Modifier,
    hourlyUiModel: HourlyUiModel,
    containerColor: Color,
    contentColor: Color
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(25.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = hourlyUiModel.time)
            LottieAnimation(
                animation = hourlyUiModel.icon,
                modifier = Modifier.size(48.dp)
            )
            Text(text = hourlyUiModel.temperature)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.water_droplettes),
                    contentDescription = null
                )
                Text(text = hourlyUiModel.pop)
            }
        }
    }
}