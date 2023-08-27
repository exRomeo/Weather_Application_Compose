package com.trianglz.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.trianglz.weatherapp.presentation.navigation.appwide.NavigationActions
import com.trianglz.weatherapp.presentation.navigation.appwide.WeatherAppNavGraph
import com.trianglz.weatherapp.presentation.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * [WindowCompat.setDecorFitsSystemWindows] set to false so the background expands behind the system's status bar
         * also need to set
         * val window = (context as Activity).window
         * window.statusBarColor = Color.Transparent.toArgb()
         * in [WeatherAppTheme] and
         * android:windowSoftInputMode="adjustResize
         * in the manifest -> activity
         * */


        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()
                val navigationActions = remember(navController) {
                    NavigationActions(navController)
                }
                WeatherAppNavGraph(
                    navigationActions = navigationActions,
                    navController = navController,
                    modifier = Modifier.background(BackgroundGradient)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        WeatherAppNavGraph(
            navigationActions = NavigationActions(rememberNavController()),
            modifier = Modifier.background(BackgroundGradient)
        )
    }
}
