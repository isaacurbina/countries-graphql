package com.iucoding.countriesgraphql.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.iucoding.countriesgraphql.R
import com.iucoding.countriesgraphql.domain.model.DetailedCountry

@Composable
fun CountryDialog(
    country: DetailedCountry,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val joinedLanguages = remember(country.languages) {
        country.languages.joinToString()
    }
    Dialog(onDismissRequest = onDismiss) {
        Column(modifier = modifier) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = country.emoji,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = country.name,
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(R.string.continent, country.continent))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.currency, country.currency))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.capital, country.capital))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.languages, joinedLanguages))
        }
    }
}

@Preview
@Composable
private fun CountryDialogPreview() {
    CountryDialog(
        country = DetailedCountry(
            code = "CA",
            name = "Canada",
            emoji = "\uD83C\uDDE8\uD83C\uDDE6",
            capital = "Ottawa",
            continent = "North America",
            currency = "CAD",
            languages = listOf("English", "French")
        ),
        onDismiss = { }
    )
}
