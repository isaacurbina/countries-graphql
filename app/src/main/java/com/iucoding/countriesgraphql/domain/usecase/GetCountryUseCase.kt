package com.iucoding.countriesgraphql.domain.usecase

import com.iucoding.countriesgraphql.domain.CountryClient
import com.iucoding.countriesgraphql.domain.model.DetailedCountry

class GetCountryUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(code: String): DetailedCountry? {
        return countryClient.getCountry(code)
    }
}
