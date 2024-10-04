package com.iucoding.countriesgraphql.domain.usecase

import com.iucoding.countriesgraphql.domain.CountryClient
import com.iucoding.countriesgraphql.domain.model.SimpleCountry

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(): List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }
}
