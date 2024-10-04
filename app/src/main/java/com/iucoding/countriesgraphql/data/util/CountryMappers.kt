package com.iucoding.countriesgraphql.data.util

import com.iucoding.CountriesQuery
import com.iucoding.CountryQuery
import com.iucoding.countriesgraphql.domain.model.DetailedCountry
import com.iucoding.countriesgraphql.domain.model.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        languages = languages.map { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital"
    )
}
