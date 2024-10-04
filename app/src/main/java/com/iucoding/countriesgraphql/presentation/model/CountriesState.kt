package com.iucoding.countriesgraphql.presentation.model

import com.iucoding.countriesgraphql.domain.model.DetailedCountry
import com.iucoding.countriesgraphql.domain.model.SimpleCountry

data class CountriesState(
    val countries: List<SimpleCountry> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCountry: DetailedCountry? = null
)
