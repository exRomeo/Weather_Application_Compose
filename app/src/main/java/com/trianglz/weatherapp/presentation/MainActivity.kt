package com.trianglz.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.trianglz.weatherapp.presentation.ui.home.HomeScreen
import com.trianglz.weatherapp.presentation.ui.home.HomeViewModel
import com.trianglz.weatherapp.presentation.ui.theme.BackgroundGradient
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        setContent {
            WeatherAppTheme {
                HomeScreen(
                    modifier = Modifier.background(BackgroundGradient),
                    viewModel = viewModel
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    WeatherAppTheme {
//        Column(modifier = Modifier.background(BackgroundGradient)) {
//            val appContext = LocalContext.current
//            val viewModel: HomeViewModel = viewModel(
//                factory = HomeViewModelFactory(
//                    repository = appContext.applicationContext.getRepository()
//                )
//            )
//            HomeScreen(viewModel = viewModel)
//        }
//    }
//}
