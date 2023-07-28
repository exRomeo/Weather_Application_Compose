package com.trianglz.weatherapp.ui.components

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WeatherSearchBar() {
    var text by remember{ mutableStateOf("Search For A City Or Airport")}
    BasicTextField(
        value = text,
        onValueChange = {text = it}
    )
}


@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_4")
@Composable
fun WeatherSearchBarPreview() {
    WeatherSearchBar()
}