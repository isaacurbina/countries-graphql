package com.iucoding.countriesgraphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.iucoding.countriesgraphql.presentation.CountriesViewModel
import com.iucoding.countriesgraphql.presentation.composable.CountriesScreen
import com.iucoding.countriesgraphql.ui.theme.CountriesGraphQLTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountriesGraphQLTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.state.collectAsState()
                CountriesScreen(
                    state = state,
                    onSelectedCountry = viewModel::selectCountry,
                    onDismissCountryDialog = viewModel::dismissCountryDialog,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
