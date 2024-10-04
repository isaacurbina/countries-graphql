package com.iucoding.countriesgraphql.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iucoding.countriesgraphql.domain.model.SimpleCountry

@Composable
fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = country.emoji,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = country.name,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = country.capital)
        }
    }
}

@Preview
@Composable
private fun CountryItemPreview() {
    CountryItem(
        country = SimpleCountry(
            code = "US",
            name = "United States",
            emoji = "\uD83C\uDDFA\uD83C\uDDF8",
            capital = "Washington D.C."
        )
    )
}
