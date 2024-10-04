package com.iucoding.countriesgraphql.data

import com.apollographql.apollo.ApolloClient
import com.iucoding.CountriesQuery
import com.iucoding.CountryQuery
import com.iucoding.countriesgraphql.data.util.toDetailedCountry
import com.iucoding.countriesgraphql.data.util.toSimpleCountry
import com.iucoding.countriesgraphql.domain.CountryClient
import com.iucoding.countriesgraphql.domain.model.DetailedCountry
import com.iucoding.countriesgraphql.domain.model.SimpleCountry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApolloCountryClient(
    private val apolloClient: ApolloClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CountryClient {

    override suspend fun getCountries(): List<SimpleCountry> {
        return withContext(dispatcher) {
            apolloClient
                .query(CountriesQuery())
                .execute()
                .data
                ?.countries
                .orEmpty()
                .map { it.toSimpleCountry() }
        }
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return withContext(dispatcher) {
            apolloClient
                .query(CountryQuery(code))
                .execute()
                .data
                ?.country
                ?.toDetailedCountry()
        }
    }
}
