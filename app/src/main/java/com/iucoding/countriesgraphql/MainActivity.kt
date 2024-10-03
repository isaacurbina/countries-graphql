package com.iucoding.countriesgraphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.iucoding.countriesgraphql.ui.theme.CountriesGraphQLTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountriesGraphQLTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TODO("continue tutorial on 13:48 https://www.youtube.com/watch?v=ME3LH2bib3g")
                }
            }
        }
    }
}
