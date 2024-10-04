package com.iucoding.countriesgraphql.domain

import com.iucoding.countriesgraphql.domain.model.DetailedCountry
import com.iucoding.countriesgraphql.domain.model.SimpleCountry

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?
}
