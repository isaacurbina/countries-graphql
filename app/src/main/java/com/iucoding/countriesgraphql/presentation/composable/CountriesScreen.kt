package com.iucoding.countriesgraphql.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.iucoding.countriesgraphql.domain.model.SimpleCountry
import com.iucoding.countriesgraphql.presentation.model.CountriesState

@Composable
fun CountriesScreen(
    state: CountriesState,
    onSelectedCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) {
                    CountryItem(
                        country = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectedCountry(it.code) }
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@PreviewScreenSizes
@Composable
private fun CountriesScreenPreview() {
    CountriesScreen(
        state = CountriesState(
            countries = listOf(
                SimpleCountry(
                    code = "CA",
                    name = "Canada",
                    emoji = "\uD83C\uDDE8\uD83C\uDDE6",
                    capital = "Ottawa."
                ),
                SimpleCountry(
                    code = "US",
                    name = "United States",
                    emoji = "\uD83C\uDDFA\uD83C\uDDF8",
                    capital = "Washington D.C."
                ),
                SimpleCountry(
                    code = "MX",
                    name = "Mexico",
                    emoji = "\uD83C\uDDF2\uD83C\uDDFD",
                    capital = "Mexico City"
                )
            )
        ),
        onSelectedCountry = {},
        onDismissCountryDialog = { })
}
